package com.example.hanulproject.main.statusTask;


import android.os.AsyncTask;
import android.util.Log;
import com.example.hanulproject.vo.StatusVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class GetStatus extends AsyncTask<Void,Void,Void> {

    StatusVO vo;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            InputStream is = new URL("http://192.168.0.92").openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String str;
            StringBuffer buffer = new StringBuffer();
            while ((str = rd.readLine()) != null) {
                buffer.append(str);
            }
            String receiveMsg = buffer.toString();

            Log.d("Arduino_test",receiveMsg);

            readjson(receiveMsg);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    private void readjson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            //vo.setMoisture((Integer) obj.get("moisture"));
            Log.d("Arduino_test", String.valueOf(obj.get("moisture")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
