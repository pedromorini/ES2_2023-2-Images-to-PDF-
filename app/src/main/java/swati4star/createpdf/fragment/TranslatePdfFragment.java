package swati4star.createpdf.fragment;

import static android.app.Activity.RESULT_OK;
import static swati4star.createpdf.util.Constants.REQUEST_CODE_FOR_WRITE_PERMISSION;
import static swati4star.createpdf.util.Constants.STORAGE_LOCATION;
import static swati4star.createpdf.util.Constants.WRITE_PERMISSIONS;
import static swati4star.createpdf.util.Constants.textExtension;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.dd.morphingbutton.MorphingButton;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateException;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import swati4star.createpdf.R;
import swati4star.createpdf.adapter.MergeFilesAdapter;
import swati4star.createpdf.interfaces.BottomSheetPopulate;
import swati4star.createpdf.interfaces.OnBackPressedInterface;
import swati4star.createpdf.util.BottomSheetCallback;
import swati4star.createpdf.util.BottomSheetUtils;
import swati4star.createpdf.util.CommonCodeUtils;
import swati4star.createpdf.util.Constants;
import swati4star.createpdf.util.DialogUtils;
import swati4star.createpdf.util.FileUtils;
import swati4star.createpdf.util.MorphButtonUtility;
import swati4star.createpdf.util.PermissionsUtils;
import swati4star.createpdf.util.RealPathUtil;
import swati4star.createpdf.util.StringUtils;

public class TranslatePdfFragment extends Fragment implements MergeFilesAdapter.OnClickListener,
        BottomSheetPopulate, OnBackPressedInterface {

    private final int mFileSelectCode = 0;
    @BindView(R.id.tv_translate_pdf_bottom)
    TextView mTextView;
    @BindView(R.id.translate_pdf)
    MorphingButton translatePdf;
    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;
    @BindView(R.id.recyclerViewFiles)
    RecyclerView mRecyclerViewFiles;
    @BindView(R.id.upArrow)
    ImageView mUpArrow;
    @BindView(R.id.downArrow)
    ImageView mDownArrow;
    @BindView(R.id.layout)
    RelativeLayout mLayout;
    @BindView(R.id.lottie_progress)
    LottieAnimationView mLottieProgress;
    private Activity mActivity;
    private FileUtils mFileUtils;
    private Uri mExcelFileUri;
    private String mRealPath;
    private BottomSheetUtils mBottomSheetUtils;
    private BottomSheetBehavior mSheetBehavior;
    private SharedPreferences mSharedPreferences;
    private MorphButtonUtility mMorphButtonUtility;
    private boolean mButtonClicked = false;
    private String mFileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_translate_pdf, container,
                false);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
        mMorphButtonUtility = new MorphButtonUtility(mActivity);
        ButterKnife.bind(this, rootView);
        mSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        mMorphButtonUtility.morphToGrey(translatePdf, mMorphButtonUtility.integer());
        translatePdf.setEnabled(false);
        mBottomSheetUtils.populateBottomSheetWithPDFs(this);
        mLottieProgress.setVisibility(View.VISIBLE);
        mSheetBehavior.setBottomSheetCallback(new BottomSheetCallback(mUpArrow, isAdded()));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mFileUtils = new FileUtils(mActivity);
        mBottomSheetUtils = new BottomSheetUtils(mActivity);
    }

    @OnClick(R.id.viewFiles)
    void onViewFilesClick(View view) {
        mBottomSheetUtils.showHideSheet(mSheetBehavior);
    }

    @OnClick(R.id.select_pdf_file)
    public void selectPdfFile() {
        if (!mButtonClicked) {
            Uri uri = Uri.parse(Environment.getRootDirectory() + "/");
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setDataAndType(uri, "*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            try {
                startActivityForResult(
                        Intent.createChooser(intent, String.valueOf(R.string.select_file)),
                        mFileSelectCode);
                mButtonClicked = true;
            } catch (android.content.ActivityNotFoundException ex) {
                StringUtils.getInstance().showSnackbar(mActivity, R.string.install_file_manager);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mButtonClicked = false;
        if (requestCode == mFileSelectCode && resultCode == RESULT_OK) {
            mExcelFileUri = data.getData();
            mRealPath = RealPathUtil.getInstance().getRealPath(getContext(), data.getData());
            StringUtils.getInstance().showSnackbar(mActivity, getResources().getString(R.string.snackbar_pdfselected));
            mFileName = mFileUtils.getFileName(mExcelFileUri);
            if (mFileName != null && !mFileName.endsWith(Constants.pdfExtension)) {
                StringUtils.getInstance().showSnackbar(mActivity, R.string.extension_not_supported);
                return;
            }
            mFileName = mActivity.getResources().getString(R.string.pdf_selected)
                    + mFileName;
            mTextView.setText(mFileName);
            mTextView.setVisibility(View.VISIBLE);
            translatePdf.setEnabled(true);
            mMorphButtonUtility.morphToSquare(translatePdf, mMorphButtonUtility.integer());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getRuntimePermissions() {
        PermissionsUtils.getInstance().requestRuntimePermissions(this,
                WRITE_PERMISSIONS,
                REQUEST_CODE_FOR_WRITE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsUtils.getInstance().handleRequestPermissionsResult(mActivity, grantResults,
                requestCode, REQUEST_CODE_FOR_WRITE_PERMISSION, this::openExtractText);
    }

    /**
     * This function is used to open up the Dialog box to enter the
     * file name.
     */
    @OnClick(R.id.translate_pdf)
    public void openExtractText() {
        PermissionsUtils.getInstance().checkStoragePermissionAndProceed(getContext(), this::openText);
    }

    private void openText() {
        new MaterialDialog.Builder(mActivity)
                .title(R.string.creating_txt)
                .content(R.string.enter_file_name)
                .input(getString(R.string.example), null, (dialog, input) -> {
                    if (StringUtils.getInstance().isEmpty(input)) {
                        StringUtils.getInstance().showSnackbar(mActivity, R.string.snackbar_name_not_blank);
                    } else {
                        final String inputName = input.toString();
                        if (!mFileUtils.isFileExist(inputName + textExtension)) {
                            translateTextFromPdf(inputName);
                        } else {
                            MaterialDialog.Builder builder = DialogUtils.getInstance().createOverwriteDialog(mActivity);
                            builder.onPositive((dialog12, which) -> translateTextFromPdf(inputName))
                                    .onNegative((dialog1, which) -> openExtractText())
                                    .show();
                        }
                    }
                })
                .show();
    }

    /**
     * This function is used to extract the text from a PDF and store
     * it in a new text file.
     *
     * @param inputName -  input pdf filename
     */
    private void translateTextFromPdf(String inputName) {
        String mStorePath = mSharedPreferences.getString(STORAGE_LOCATION,
                StringUtils.getInstance().getDefaultStorageLocation());
        String mPath = mStorePath + inputName + textExtension;
        try {
            StringBuilder parsedText = new StringBuilder();
            PdfReader reader = new PdfReader(mRealPath);
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                parsedText.append(PdfTextExtractor.getTextFromPage(reader, i + 1)
                        .trim()).append("\n"); //Extracting the content from the different pages
            }
            reader.close();
            // Check whether there is no text found from the PDF Doc
            if (TextUtils.isEmpty(parsedText.toString().trim())) {
                StringUtils.getInstance().showSnackbar(mActivity, R.string.snack_bar_empty_txt_in_pdf);
                return;
            }

            // Translate the original text using Google Translate API
            String translatedText = translateText(parsedText.toString(), "en", "pt");

            File textFile = new File(mStorePath, inputName + textExtension);
            FileWriter writer = new FileWriter(textFile);
            writer.append(translatedText);
            writer.flush();
            writer.close();
            StringUtils.getInstance().getSnackbarwithAction(mActivity, R.string.snackbar_txtExtracted)
                    .setAction(R.string.snackbar_viewAction,
                            v -> mFileUtils.openFile(mPath, FileUtils.FileType.e_TXT))
                    .show();
            mTextView.setVisibility(View.GONE);
            mButtonClicked = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mMorphButtonUtility.morphToGrey(translatePdf, mMorphButtonUtility.integer());
            translatePdf.setEnabled(false);
            mRealPath = null;
            mExcelFileUri = null;
        }
    }
    /**
     *
     * This function is used to translate a PDF with an Google translate API
     */

    private String translateText(String originalText, String sourceLanguage, String targetLanguage) throws TranslateException {

        // Crie uma instância do cliente de tradução com a chave de API
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyDFFS5zY135MKjne9ItxNW-WcQam3RDvGI").build().getService();

        Translation translation = translate.translate(originalText,
                TranslateOption.sourceLanguage(sourceLanguage),
                TranslateOption.targetLanguage(targetLanguage));

        return translation.getTranslatedText();
    }

    @Override
    public void onPopulate(ArrayList<String> paths) {
        CommonCodeUtils.getInstance().populateUtil(mActivity, paths,
                this, mLayout, mLottieProgress, mRecyclerViewFiles);
    }

    @Override
    public void onItemClick(String path) {
        mSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mRealPath = path;
        mFileName = FileUtils.getFileName(path);
        mFileName = getResources().getString(R.string.pdf_selected) + mFileName;
        mTextView.setText(mFileName);
        mTextView.setVisibility(View.VISIBLE);
        translatePdf.setEnabled(true);
        mMorphButtonUtility.morphToSquare(translatePdf, mMorphButtonUtility.integer());
    }

    @Override
    public void closeBottomSheet() {
        CommonCodeUtils.getInstance().closeBottomSheetUtil(mSheetBehavior);
    }

    @Override
    public boolean checkSheetBehaviour() {
        return CommonCodeUtils.getInstance().checkSheetBehaviourUtil(mSheetBehavior);
    }
}
