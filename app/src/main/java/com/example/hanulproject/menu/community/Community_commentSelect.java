package com.example.hanulproject.menu.community;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.hanulproject.task.adapter.CommunityCommentAdpater;
import com.example.hanulproject.task.task.ReadMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Community_commentSelect extends AsyncTask<Void,Void,Void> {
    ArrayList<Community_commentVO> cmmcList;
    CommunityCommentAdpater adpater;
    int no;

    public Community_commentSelect(ArrayList<Community_commentVO> cmmcList, CommunityCommentAdpater adpater, int no){
        this.cmmcList = cmmcList;
        this.adpater = adpater;
        this.no = no;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String postURL = ipConfig + "/AA/cmmcSelect?no="+no;

        try {
            //MultipartEntityBuild  생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            //전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());

            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ((AndroidHttpClient) httpClient).close();
        }
        return null;
    }

    private void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                cmmcList.add(readMessage(reader));
            }
        }finally {
            reader.close();
        }
    }

    private Community_commentVO readMessage(JsonReader reader) throws IOException{
        int no=0, comu_no=0;
        String title="", content="", writer="";

        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("no")){
                no = reader.nextInt();
            }else if (readStr.equals("comu_no")){
                comu_no = reader.nextInt();
            }else if (readStr.equals("content")){
                content = reader.nextString();
            }else if (readStr.equals("writer")){
                writer = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();

        return new Community_commentVO(no,comu_no,content,writer);
    }
}
