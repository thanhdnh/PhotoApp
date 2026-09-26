package thanhdnh.ueh.edu.article_app;


import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;


public class Downloader {
    public static String cached_file_path = "";


    public static File downloadFile(String url, File cached) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(url).build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) return null;
            String contentType = response.header("Content-Type", "");
            String extension = getExtensionFromMimeType(contentType);
            File file = File.createTempFile("downloaded_file", extension, cached);
            if (response.body() != null) {
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                sink.writeAll(response.body().source());
                sink.close();
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void downloadWithProgress(String inputurl, Handler mainHandler, Context context, File where2store, ProgressBar progressBar, ImageView imageView) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(inputurl).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mainHandler.post(() -> {
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                });
            }


            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    mainHandler.post(() -> {});
                    return;
                }


                long totalBytes = response.body().contentLength();
                InputStream inputStream = response.body().byteStream();
                String contentType = response.header("Content-Type", "");
                String extension = getExtensionFromMimeType(contentType);


                try (OutputStream outputStream = new FileOutputStream(where2store + "/downloaded_file" + extension)) {
                    byte[] buffer = new byte[8192];
                    long downloadedBytes = 0;
                    int bytesRead;


                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        downloadedBytes += bytesRead;
                        int progress = (int) ((downloadedBytes * 100) / totalBytes);
                        mainHandler.post(() -> progressBar.setProgress(progress));
                    }
                    outputStream.flush();


                    mainHandler.post(() -> {
                        cached_file_path = where2store + "/downloaded_file" + extension;
                        imageView.setImageURI(Uri.parse(cached_file_path));
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                    });
                } catch (Exception e) {
                    mainHandler.post(() -> {});
                }
            }
        });
    }


    private static String getExtensionFromMimeType(String mimeType) {
        Map<String, String> mimeMap = new HashMap<>();
        mimeMap.put("image/jpeg", ".jpg");
        mimeMap.put("image/png", ".png");
        mimeMap.put("application/json", ".json");
        mimeMap.put("text/plain", ".json");
        return mimeMap.getOrDefault(mimeType, "");
    }
}

