package swati4star.createpdf.util;

import static swati4star.createpdf.util.Constants.pdfDirectory;
import static swati4star.createpdf.util.Constants.tempDirectory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import swati4star.createpdf.R;

public class DirectoryUtils {
    private final Context mContext;
    private final SharedPreferences mSharedPreferences;

    public DirectoryUtils(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Creates a new folder for temp files
     */
    public static void makeAndClearTemp() {
        String dest = Environment.getExternalStorageDirectory().toString() +
                pdfDirectory + tempDirectory;
        File folder = new File(dest);
        boolean result = folder.mkdir();
        // Clear all the files in it, if any
        if (result && folder.isDirectory()) {
            String[] children = folder.list();
            if (children != null) {
                for (String child : children) {
                    File childFile = new File(folder, child);
                    boolean deletionResult = childFile.delete();
                    if (!deletionResult) {
                        Log.e("DirectoryUtils", "Failed to delete file: " + childFile.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Used to search for PDFs matching the search query
     *
     * @param query - Query from search bar
     * @return ArrayList containing all the pdf files matching the search query
     */
    public ArrayList<File> searchPDF(String query) {
        ArrayList<File> searchResult = new ArrayList<>();
        final File[] files = getOrCreatePdfDirectory().listFiles();
        ArrayList<File> pdfs = searchPdfsFromPdfFolder(files);
        for (File pdf : pdfs) {
            String path = pdf.getPath();
            String[] fileName = path.split("/");
            String pdfName = fileName[fileName.length - 1].replace("pdf", "");
            if (checkChar(query, pdfName) == 1) {
                searchResult.add(pdf);
            }
        }
        return searchResult;
    }

    /**
     * Checks the number of characters matching between the query and the file name
     *
     * @param query    - Query from search bar
     * @param fileName - Name of PDF file
     * @return 1 if the search query and filename have the same characters, otherwise 0
     */
    private int checkChar(String query, String fileName) {
        query = query.toLowerCase();
        fileName = fileName.toLowerCase();
        int count = 0;
        for (int i = 0; i < query.length() && i < fileName.length(); i++) {
            if (query.charAt(i) == fileName.charAt(i)) {
                count++;
            }
        }
        return count == Math.min(query.length(), fileName.length()) ? 1 : 0;
    }

    /**
     * Returns pdf files from folder
     *
     * @param files list of files (folder)
     */
    private ArrayList<File> getPdfsFromPdfFolder(File[] files) {
        ArrayList<File> pdfFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (isPDFAndNotDirectory(file)) {
                    pdfFiles.add(file);
                }
            }
        }
        return pdfFiles;
    }

    /**
     * Searches for PDF files within a directory
     *
     * @param files array of files to search through
     * @return list of PDF files found
     */
    private ArrayList<File> searchPdfsFromPdfFolder(File[] files) {
        ArrayList<File> pdfFiles = getPdfsFromPdfFolder(files);
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    for (File dirFiles : file.listFiles()) {
                        if (isPDFAndNotDirectory(dirFiles)) {
                            pdfFiles.add(dirFiles);
                        }
                    }
                }
            }
        }
        return pdfFiles;
    }

    /**
     * Checks if a given file is a PDF and not a directory
     *
     * @param file - Input file
     * @return true if the condition satisfies, else false
     */
    private boolean isPDFAndNotDirectory(File file) {
        return !file.isDirectory() && file.getName().endsWith(mContext.getString(R.string.pdf_ext));
    }

    /**
     * Get or create the PDF directory if it does not exist
     *
     * @return File representing the PDF directory
     */
    public File getOrCreatePdfDirectory() {
        File folder = new File(mSharedPreferences.getString(STORAGE_LOCATION, StringUtils.getInstance().getDefaultStorageLocation()));
        if (!folder.exists()) {
            boolean result = folder.mkdir();
            if (!result) {
                Log.e("DirectoryUtils", "Failed to create PDF directory: " + folder.getAbsolutePath());
            }
        }
        return folder;
    }
}
