package com.example.hanulproject.task.task.detail;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.hanulproject.vo.ComplainVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class ComplainCallDetail extends AsyncTask<Void,Void, ComplainVO> {
    int no;

    String postURL = null;

    public ComplainCallDetail(int no){
        this.no = no;
    }

    @Override
    protected ComplainVO doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        postURL = ipConfig + "/AA/cpdetail?no="+no;

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

            return readJsonStream(inputStream);

        } catch (Exception e){
            e.printStackTrace();
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
    protected void onPostExecute(ComplainVO complainVO) {
        super.onPostExecute(complainVO);
    }

    private ComplainVO readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        ComplainVO vo = null;
        reader.beginObject();
        try {

            vo = (ReadMessage(reader));

        }finally {
            reader.close();
        }
        return vo;
    }

    public ComplainVO ReadMessage(JsonReader reader) throws IOException{
        int no = 0;
        String title="", content="", writer="", filename="", filepath="";

        //reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("no")){
                no = reader.nextInt();
            }else if (readStr.equals("title")){
                title = reader.nextString();
            }else if (readStr.equals("content")){
                content = reader.nextString();
            }else if (readStr.equals("writerID")){
                writer = reader.nextString();
            }/*else if (readStr.equals("writedate")){
                writedate = reader.nextString();
            }*/else if (readStr.equals("fileName")){
                filename = reader.nextString();
            }else if (readStr.equals("filePath")){
                filepath = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();

        return new ComplainVO(no, title, content, writer, filename, filepath);
    }
}
