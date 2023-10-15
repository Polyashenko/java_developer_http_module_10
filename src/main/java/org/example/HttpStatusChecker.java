package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class HttpStatusChecker {
    private OkHttpClient client = new OkHttpClient();

    public String getStatusImage(int code) throws IOException {
        String url = "https://http.cat/" + code + ".jpg";
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        if (response.isSuccessful()) {
            return url;
        } else {
            throw new IOException("Зображення для статусу " + code + " недоступне");
        }
    }
}
