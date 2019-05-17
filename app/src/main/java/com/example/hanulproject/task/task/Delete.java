package com.example.hanulproject.task.task;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Delete extends AsyncTask<Void,Void,Void> {

    int no;
    int controller;


    public Delete(int no, int controller){
        this.no = no;
        this.controller = controller;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;
        String postURL = "";
        try {
            //MultipartEntityBuilder 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //문자열 및 데이터 추가
            builder.addTextBody("no", String.valueOf(no), ContentType.create("Multipart/related", "UTF-8"));

            if(controller == 1){
                postURL = ipConfig + "/AA/ndelete";

            } else if(controller == 2){
                postURL = ipConfig + "/AA/cpdelete";

            } else if(controller == 3){
                postURL = ipConfig + "/AA/cmdelete";

            } else if(controller == 4){
                postURL = ipConfig + "/AA/sdelete";

            } else if(controller == 5){
                postURL = ipConfig + "/AA/udelete";

            } else if(controller == 6){
                postURL = ipConfig + "/AA/stdelete";

            }
            //전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");

            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        }
        catch (Exception e) {
            e.getMessage();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            ((AndroidHttpClient) httpClient).close();
        }
        return null;
    }
}
