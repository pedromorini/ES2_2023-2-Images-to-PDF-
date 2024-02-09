package pacoteswati4star.createpdf.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PdfRenderer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import swati4star.createpdf.interfaces.ExtractImagesListener;

public class PDFToImages extends AsyncTask<Void, Void, Void> {

    private final String mPath;
    private final Uri mUri;
    private final ExtractImagesListener mExtractImagesListener;
    private final String[] mSenha;
    private final Context mContext;
    private int mImagesCount = 0;
    private List<String> mOutputFilePaths;
    private PDFEncryptionUtility mPDFEncryptionUtility;
    private String mDecryptedPath;

    public PDFToImages(Context contexto, String[] senha, String path, Uri uri,
                       ExtractImagesListener extractImagesListener) {
        this.mPath = path;
        this.mUri = uri;
        this.mExtractImagesListener = extractImagesListener;
        this.mOutputFilePaths = new ArrayList<>();
        this.mSenha = senha;
        this.mContext = contexto;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mPDFEncryptionUtility = new PDFEncryptionUtility((Activity) mContext);
        mExtractImagesListener.extractionStarted();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (mSenha != null) {
            mDecryptedPath = mPDFEncryptionUtility.removeDefPasswordForImages(mPath, mSenha);
        }
        mOutputFilePaths = new ArrayList<>();
        mImagesCount = 0;

        ParcelFileDescriptor fileDescriptor = null;
        try {
            if (mDecryptedPath != null)
                fileDescriptor = ParcelFileDescriptor.open(new File(mDecryptedPath), ParcelFileDescriptor.MODE_READ_ONLY);
            else {
                if (mUri != null && mContext != null) {
                    fileDescriptor = mContext.getContentResolver().openFileDescriptor(mUri, "r");
                } else if (mPath != null) {
                    fileDescriptor = ParcelFileDescriptor.open(new File(mPath), ParcelFileDescriptor.MODE_READ_ONLY);
                }
            }
            if (fileDescriptor != null) {
                PdfRenderer renderer = new PdfRenderer(fileDescriptor);
                final int pageCount = renderer.getPageCount();
                for (int i = 0; i < pageCount; i++) {
                    PdfRenderer.Page page = renderer.openPage(i);
                    Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    canvas.drawColor(Color.WHITE);
                    canvas.drawBitmap(bitmap, 0, 0, null);
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    page.close();
                    String fileName = FileUtils.getFileNameWithoutExtension(mPath) +
                            "_" + (i + 1);
                    String path = ImageUtils.saveImage(fileName, bitmap);
                    if (path != null) {
                        mOutputFilePaths.add(path);
                        mImagesCount++;
                    }
                }
                renderer.close();
            }
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mExtractImagesListener.updateView(mImagesCount, mOutputFilePaths);
        if (mDecryptedPath != null) {
            boolean deleteSuccess = new File(mDecryptedPath).delete();
            if (!deleteSuccess) {
                Log.e("PDFToImages", "Failed to delete file: " + mDecryptedPath);
            }
        }
    }
}
