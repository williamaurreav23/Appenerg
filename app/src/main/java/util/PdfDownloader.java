package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PdfDownloader extends AsyncTask<Void, String, Void> {

    private Context context;
    private String pdfUrl;
    private String pdfName;
    private File downloadedFile;
    private ProgressDialog barProgressDialog;
    private OnDownloadStatus onDownloadStatus;

    private static final int MEGABYTE = 1024 * 1024;

    public PdfDownloader(Context context, String pdfUrl, String pdfName, OnDownloadStatus onDownloadStatus) {
        this.context = context;
        this.pdfUrl = pdfUrl;
        this.pdfName = pdfName;
        this.onDownloadStatus = onDownloadStatus;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        barProgressDialog = new ProgressDialog(context);
        barProgressDialog.setTitle("Downloading File ...");
        barProgressDialog.setMessage("Download in progress ...");
        barProgressDialog.show();
        barProgressDialog.setCancelable(false);
        barProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "Education_PDF");
        folder.mkdir();

        File pdfFile = new File(folder, pdfName);

        try {
            pdfFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadFile2(pdfUrl, pdfFile);

        downloadedFile = new File(Environment.getExternalStorageDirectory(), pdfName);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        barProgressDialog.dismiss();
        onDownloadStatus.onDownloaded(downloadedFile);
    }

    private static void downloadFile(String fileUrl, File directory) {
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile2(String url, File outputFile) {
        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            return; // swallow a 404
        } catch (IOException e) {
            return; // swallow a 404
        }
    }

    public interface OnDownloadStatus {
        public void onDownloaded(File file);
    }

}
