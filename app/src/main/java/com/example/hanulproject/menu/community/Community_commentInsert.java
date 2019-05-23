package com.example.hanulproject.menu.community;

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
import java.nio.charset.Charset;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Community_commentInsert extends AsyncTask<Void,Void,Void> {

    String content, writer;
    int comu_no;

    public Community_commentInsert(String content, int comu_no, String writer){
        this.content = content;
        this.comu_no = comu_no;
        this.writer = writer;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String postURL = ipConfig + "/AA/cm_cmt_insert";

        try{
            //MultipartEntityBuilder 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //문자열 및 데이터 추가
            builder.addTextBody("comu_no", String.valueOf(comu_no), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("writer", writer, ContentType.create("Multipart/related", "UTF-8"));

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
