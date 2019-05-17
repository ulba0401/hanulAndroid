package com.example.hanulproject.task.task;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.task.adapter.CommunityAdapter;
import com.example.hanulproject.task.adapter.ComplainAdapter;
import com.example.hanulproject.task.adapter.NoticeAdapter;
import com.example.hanulproject.task.adapter.SettingAdapter;
import com.example.hanulproject.task.adapter.StatusAdapter;
import com.example.hanulproject.task.adapter.UserAdapter;
import com.example.hanulproject.vo.CommunityVO;
import com.example.hanulproject.vo.ComplainVO;
import com.example.hanulproject.vo.NoticeVO;
import com.example.hanulproject.vo.SettingVO;
import com.example.hanulproject.vo.StatusVO;
import com.example.hanulproject.vo.UserVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Insert extends AsyncTask<Void, Void, Void> {

    String title, content;
    String uploadType; // 업로드 타입 현재는 image 뿐
    String imageUploadPathA; //디비에 입력할 이미지 경로
    String imageFilePathA; // 실제 업로드할 이미지 경로
    String uploadFileName;
    int controller;
    String writer;

    public void setWriter() {
        this.writer = LoginRequest.vo.getEmail();
    }

    public Insert(String title, String content, int controller) {
        this.title = title;
        this.content = content;
        this.controller = controller;
        setWriter();
    }

    public Insert(String title, String content, int controller, String uploadType, String imageFilePathA, String imageUploadPathA, String uploadFileName) {
        this.title = title;
        this.content = content;
        this.controller = controller;
        this.uploadType = uploadType;
        this.imageFilePathA = imageFilePathA;
        this.imageUploadPathA = imageUploadPathA;
        this.uploadFileName = uploadFileName;
        setWriter();
    }

    String postURL = null;

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        try {
            //MultipartEntityBuilder 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //문자열 및 데이터 추가
            builder.addTextBody("title", title, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("writer", writer, ContentType.create("Multipart/related", "UTF-8"));

            if(uploadType != null && uploadType.equals("image")){
                builder.addTextBody("uploadType", uploadType, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("fileName", uploadFileName, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("dbImgPath", imageUploadPathA, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image", new FileBody(new File(imageFilePathA)));
            }

            if(controller == 1){
                postURL = ipConfig + "/AA/ninsert";

            } else if(controller == 2){
                postURL = ipConfig + "/AA/cpinsert";

            } else if(controller == 3){
                postURL = ipConfig + "/AA/cminsert";

            } else if(controller == 4){
                postURL = ipConfig + "/AA/sinsert";

            } else if(controller == 5){
                postURL = ipConfig + "/AA/uinsert";

            } else if(controller == 6){
                postURL = ipConfig + "/AA/stinsert";
                //httpPost = new HttpPost(postURL);
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("insert:imageFilePath1", "추가성공");
    }
}