package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;
import java.io.IOException;

public class HttpStatusImageDownloader {
    private HttpStatusChecker checker = new HttpStatusChecker();
    private OkHttpClient client = new OkHttpClient();

    public void downloadStatusImage(int code) throws IOException {
        String imageUrl = checker.getStatusImage(code);

        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String[] parts = imageUrl.split("/");
                String filename = parts[parts.length - 1];

                try (InputStream inputStream = responseBody.byteStream();
                     FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Зображення з кодом " + code + " було успішно завантажено та збережено як " + filename);
                }
            } else {
                throw new IOException("Помилка: " + response.code());
            }
        }
    }

}
