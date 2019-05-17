package com.example.hanulproject.task.task.detail;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.hanulproject.vo.NoticeVO;

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

public class NoticeCallDetail extends AsyncTask<Void, Void, NoticeVO> {
    int no;

    public NoticeCallDetail(int no){
        this.no = no;
    }

    String postURL = null;

    @Override
    protected NoticeVO doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        NoticeVO vo = null;

        postURL = ipConfig + "/AA/ndetail?no="+no;

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
    protected void onPostExecute(NoticeVO noticeVO) {
        super.onPostExecute(noticeVO);
    }

    private NoticeVO readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        NoticeVO vo = null;
        reader.beginObject();
        try {

                vo = (ReadMessage(reader));

        }finally {
            reader.close();
        }
        return vo;
    }

    public NoticeVO ReadMessage(JsonReader reader) throws IOException{
        int no = 0, readcnt = 0;
        String title="", content="", writedate="", filename="", filepath="";

        //reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("no")){
                no = reader.nextInt();
            }else if (readStr.equals("title")){
                title = reader.nextString();
            }else if (readStr.equals("content")){
                content = reader.nextString();
            }else if (readStr.equals("date")){
                writedate = reader.nextString();
            }else if (readStr.equals("readCnt")){
                readcnt = reader.nextInt();
            }else if (readStr.equals("fileName")){
                filename = reader.nextString();
            }else if (readStr.equals("filePath")){
                filepath = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();

        Log.d("abc" , no + title + content + writedate + readcnt + filename + filepath);

        return new NoticeVO(no,title,content,writedate, readcnt, filename,filepath);
    }
}
