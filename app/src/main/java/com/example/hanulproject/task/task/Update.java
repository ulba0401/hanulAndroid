package com.example.hanulproject.task.task;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

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

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Update extends AsyncTask<Void,Void,Void> {

    NoticeVO nvo;
    ComplainVO cpvo;
    CommunityVO cmvo;
    SettingVO svo;
    StatusVO stvo;
    UserVO uvo;
    String uploadType, imageFilePathA, imageUploadPathA, uploadFileName;

    int controller;

    public void setFileInfo(String uploadType,String imageFilePathA, String imageUploadPathA, String uploadFileName){
        this.uploadType = uploadType;
        this.imageFilePathA = imageFilePathA;
        this.imageUploadPathA = imageUploadPathA;
        this.uploadFileName = uploadFileName;
    }

    public Update(NoticeVO nvo){
        this.nvo = nvo;
        this.controller = 1;
    }
    public Update(ComplainVO cpvo){
        this.cpvo = cpvo;
        this.controller = 2;
    }
    public Update(CommunityVO cmvo){
        this.cmvo = cmvo;
        this.controller = 3;
    }
    public Update(SettingVO svo){
        this.svo = svo;
        this.controller = 4;
    }
    public Update(UserVO uvo){
        this.uvo = uvo;
        this.controller = 5;
    }
    public Update(StatusVO stvo){
        this.stvo = stvo;
        this.controller = 6;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        String result = "";
        String postURL = "";

        if(controller == 1){
            postURL = ipConfig + "/AA/nupdate";

        } else if(controller == 2){
            postURL = ipConfig + "/AA/cpupdate";
            builder.addTextBody("no", String.valueOf(cpvo.getNo()), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("title", cpvo.getTitle(), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", cpvo.getContent(), ContentType.create("Multipart/related", "UTF-8"));
            if(uploadType != null && uploadType.equals("image")){
                builder.addTextBody("uploadType", uploadType, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("fileName", uploadFileName, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("dbImgPath", imageUploadPathA, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image", new FileBody(new File(imageFilePathA)));
            }
        } else if(controller == 3){
            postURL = ipConfig + "/AA/cmupdate";
            builder.addTextBody("no", String.valueOf(cmvo.getNo()), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("title", cmvo.getTitle(), ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", cmvo.getContent(), ContentType.create("Multipart/related", "UTF-8"));
            if(uploadType != null && uploadType.equals("image")){
                builder.addTextBody("uploadType", uploadType, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("fileName", uploadFileName, ContentType.create("Multipart/related", "UTF-8"));
                builder.addTextBody("dbImgPath", imageUploadPathA, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image", new FileBody(new File(imageFilePathA)));
            }
        } else if(controller == 4){
            postURL = ipConfig + "/AA/supdate";

        } else if(controller == 5){
            postURL = ipConfig + "/AA/uupdate";

        } else if(controller == 6){
            postURL = ipConfig + "/AA/stupdate";
        }

        try {
            //MultipartEntityBuilder 생성

            //문자열 및 데이터 추가

            //전송
            InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");

            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}
