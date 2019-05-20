package com.example.hanulproject.login.search;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.hanulproject.vo.UserVO;

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

public class FindId extends AsyncTask<Void, Void, Integer> {

    Context context;
    public static UserVO vo = new UserVO();
    private String name, email, id;
    boolean is_check = true;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public FindId(String id, String name, String email, Context context) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String postURL = ipConfig + "/AA/AFindId?name="+name+"email"+email;
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

            readJsonStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((AndroidHttpClient) httpClient).close();
            if (vo.getResult() != null && vo.getResult().equals("fail")) {
                return 0;
            }

            return 1;
        }
    }

    private void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                readMessage(reader);
            }
            reader.endObject();
        } finally {
            reader.close();
        }
    }

    private void readMessage(JsonReader reader) throws IOException {
        String name = "", phone = "", addr = "", id = "", pw = "", email = "", result = "";

        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("result")) {
                result = reader.nextString();
                vo.setResult(result);
            } else if (readStr.equals("name")) {
                name = reader.nextString();
                vo.setName(name);
                vo.setResult("success");
            } else if (readStr.equals("phone")) {
                phone = reader.nextString();
                vo.setPhone(phone);
            } else if (readStr.equals("addr")) {
                addr = reader.nextString();
                vo.setAddr(addr);
            } else if (readStr.equals("id")) {
                id = reader.nextString();
                vo.setId(id);
            } else if (readStr.equals("pw")) {
                pw = reader.nextString();
                vo.setPw(pw);
            } else if (readStr.equals("email")) {
                email = reader.nextString();
                vo.setEmail(email);
            } else {
                reader.skipValue();
            }
        }
    }
}
