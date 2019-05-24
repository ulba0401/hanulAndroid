package com.example.hanulproject.join;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class JoinInsert extends AsyncTask<Void, Void, Void> {

    String jname, jid, jpw, jemail;

    public JoinInsert(String name, String id, String pw, String email) {
        this.jname = name;
        this.jid = id;
        this.jpw= pw;
        this.jemail=email;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addTextBody("name", jname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("id", jid, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pw", jpw, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("email", jemail, ContentType.create("Multipart/related", "UTF-8"));

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

