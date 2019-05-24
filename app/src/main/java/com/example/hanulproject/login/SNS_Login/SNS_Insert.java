package com.example.hanulproject.login.SNS_Login;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.renderscript.RenderScript;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class SNS_Insert extends AsyncTask<Void, Void, Void> {

    String name, email, pw, id, addr, issns;

    public SNS_Insert(String name, String email){
        this.name = name;
        this.email = email;
        this.pw=pw;
        this.id=id;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addTextBody("name", name, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("id", email, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("email", email, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pw", pw,  ContentType.create("Multipart/related", "UTF-8"));
            String postURL = ipConfig + "/AA/uinsert";

            InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");

            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
