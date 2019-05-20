package com.example.hanulproject.firebase;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class DeviceToken extends AsyncTask<Void,Void,Void> {

    String token, id, email;
    int is_check;


    public DeviceToken(String token, String id){
        this.token = token;
        this.id = id;
    }

    // is_check의 값이 1이면 id, 2이면 email 로 받음
    public DeviceToken(String token, String check, int is_check){
        this.token = token;
        this.is_check = is_check;
        if(is_check == 1){
            this.id = check;
        }else if(is_check == 2){
            this.email = check;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {

        String postURL = null;

        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        postURL = ipConfig + "/AA/pushMessege?token="+token+"&id="+id;
//        if(is_check == 1){
//
//        }else if(is_check == 2){
//            postURL = ipConfig + "/AA/pushMessege?token="+token+"&email="+email+"&check="+is_check;
//        }

        try {
            //MultipartEntityBuild  생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());

            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ((AndroidHttpClient) httpClient).close();
        }
        return null;
    }


}
