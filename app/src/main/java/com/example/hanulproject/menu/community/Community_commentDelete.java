package com.example.hanulproject.menu.community;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.hanulproject.task.adapter.CommunityCommentAdpater;

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

public class Community_commentDelete extends AsyncTask<Void,Void,Void> {
    int no;
    CommunityCommentAdpater adpater;


    public Community_commentDelete(int no, CommunityCommentAdpater adpater){
        this.no = no;
        this.adpater = adpater;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String postURL = ipConfig + "/AA/cm_cmt_delete";

        try{
            //MultipartEntityBuilder 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //문자열 및 데이터 추가
            builder.addTextBody("no", String.valueOf(no), ContentType.create("Multipart/related", "UTF-8"));

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
            //adpater.is_break = false;
        }


        return null;
    }
}
