package com.example.hanulproject.main.statusTask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.hanulproject.login.LoginRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;
import java.nio.charset.Charset;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Window_on_off extends AsyncTask<Void,Void,Void> {
    ProgressDialog progressDialog;
    int is_window;
    String id = LoginRequest.vo.getId();

    public Window_on_off(ProgressDialog progressDialog, int is_window) {
        this.progressDialog = progressDialog;
        this.is_window = is_window;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("기다려주세요...");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;
        String postURL = null;
        if(is_window == 2){
            postURL = ipConfig + "/AA/controll_windowOpen?id=" + id;
        }else if(is_window == 1){
            postURL = ipConfig + "/AA/controll_windowClose?id=" + id;
        }

        try {
            //MultipartEntityBuilder 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");

            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((AndroidHttpClient) httpClient).close();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }
}
