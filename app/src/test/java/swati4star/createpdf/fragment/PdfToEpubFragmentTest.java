package swati4star.createpdf.fragment;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class PdfToEpubFragmentTest {

    @Mock
    Context mockContext;

    @Mock
    Activity mockActivity;

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    BottomSheetUtils mockBottomSheetUtils;

    @Mock
    FileUtils mockFileUtils;

    @Test
    public void onCreateView_ShouldPopulateBottomSheet() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.mSharedPreferences = mockSharedPreferences;
        fragment.mBottomSheetUtils = mockBottomSheetUtils;
        fragment.mActivity = mockActivity;
        fragment.onCreateView(null, null);
        verify(mockSharedPreferences).getString(Constants.STORAGE_LOCATION, StringUtils.getInstance().getDefaultStorageLocation());
        verify(mockBottomSheetUtils).populateBottomSheetWithPDFs(fragment);
    }

    @Test
    public void onAttach_ShouldInitializeDependencies() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.onAttach(mockContext);
        assertNotNull(fragment.mSharedPreferences);
        assertNotNull(fragment.mBottomSheetUtils);
        assertNotNull(fragment.mFileUtils);
    }

    @Test
    public void selectPdfFile_ShouldStartActivityForResult() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.mButtonClicked = false;
        fragment.mActivity = mockActivity;
        when(mockActivity.getString(R.string.select_file)).thenReturn("Select File");
        fragment.selectPdfFile();
        assertTrue(fragment.mButtonClicked);
        verify(mockActivity).startActivityForResult(any(Intent.class), eq(fragment.mFileSelectCode));
    }

    @Test
    public void onActivityResult_ShouldHandleResultCorrectly() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.mButtonClicked = true;
        fragment.mActivity = mockActivity;
        fragment.mFileUtils = mockFileUtils;
        fragment.mTextView = mock(TextView.class);
        fragment.mRealPath = "sample_real_path";
        fragment.mExcelFileUri = mock(Uri.class);
        when(mockFileUtils.getFileName(fragment.mExcelFileUri)).thenReturn("sample_file_name.pdf");
        when(mockActivity.getResources().getString(R.string.pdf_selected)).thenReturn("PDF Selected ");
        Intent mockData = mock(Intent.class);
        when(mockData.getData()).thenReturn(fragment.mExcelFileUri);
        fragment.onActivityResult(fragment.mFileSelectCode, RESULT_OK, mockData);
        verify(fragment.mTextView).setText("PDF Selected sample_file_name.pdf");
        verify(fragment.mTextView).setVisibility(View.VISIBLE);
        verify(fragment.pdfToEpub).setEnabled(true);
        verify(fragment.mMorphButtonUtility).morphToSquare(fragment.pdfToEpub, fragment.mMorphButtonUtility.integer());
    }

    @Test
    public void getRuntimePermissions_ShouldRequestWritePermissions() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.mActivity = mockActivity;
        fragment.getRuntimePermissions();
        verify(mockActivity).requestPermissions(eq(Constants.WRITE_PERMISSIONS), eq(Constants.REQUEST_CODE_FOR_WRITE_PERMISSION));
    }

    @Test
    public void onRequestPermissionsResult_ShouldHandleWritePermissionResultCorrectly() {
        PdfToEpubFragment fragment = new PdfToEpubFragment();
        fragment.mActivity = mockActivity;
        fragment.onRequestPermissionsResult(Constants.REQUEST_CODE_FOR_WRITE_PERMISSION, new String[]{}, new int[]{});
        verify(mockActivity).checkStoragePermissionAndProceed(eq(fragment.getContext()), any(Runnable.class));
    }
}
