package com.example.hanulproject.main.statusTask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.vo.StatusVO;

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

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class StatusSelect extends AsyncTask<Void,Void, StatusVO> {

    private String id = LoginRequest.vo.getId();
    StatusVO vo;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected StatusVO doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String postURL = ipConfig + "/AA/select.status?id="+id;

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

            vo = readJsonStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ((AndroidHttpClient) httpClient).close();
        }

        return vo;
    }

    @Override
    protected void onPostExecute(StatusVO statusVO) {
        super.onPostExecute(statusVO);
    }

    private StatusVO readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        StatusVO vo = null;
        reader.beginObject();
        try {
            vo = (ReadMessage(reader));
        }finally {
            reader.close();
        }
        return vo;
    }

    public StatusVO ReadMessage(JsonReader reader) throws IOException{
        String id = "", light = "", secure = "", weather = "";
        int water = 0, temper = 0, dust = 0;

        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("id")){
                id = reader.nextString();
            }else if (readStr.equals("light")){
                light = reader.nextString();
            }else if (readStr.equals("secure")){
                secure = reader.nextString();
            }else if (readStr.equals("weather")){
                weather = reader.nextString();
            }else if (readStr.equals("water")){
                water = reader.nextInt();
            }else if (readStr.equals("temper")){
                temper = reader.nextInt();
            }else if (readStr.equals("dust")){
                dust = reader.nextInt();
            }else{
                reader.skipValue();
            }
        }

        reader.endObject();

        Log.d("status" , id + light + secure + weather + water + temper + dust);

        return new StatusVO(id, light, secure, weather, water, temper, dust);
    }

}
