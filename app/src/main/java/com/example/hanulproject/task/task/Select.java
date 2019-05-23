package com.example.hanulproject.task.task;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.hanulproject.task.adapter.CommunityAdapter;
import com.example.hanulproject.task.adapter.ComplainAdapter;
import com.example.hanulproject.task.adapter.MyhomeAdapter;
import com.example.hanulproject.task.adapter.NoticeAdapter;
import com.example.hanulproject.task.adapter.SettingAdapter;
import com.example.hanulproject.task.adapter.StatusAdapter;
import com.example.hanulproject.task.adapter.UserAdapter;
import com.example.hanulproject.vo.CommunityVO;
import com.example.hanulproject.vo.ComplainVO;
import com.example.hanulproject.vo.MyhomeVO;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class Select extends AsyncTask<Void,Void,Void> {
    ArrayList<NoticeVO> nlist;
    ArrayList<ComplainVO> cplist;
    ArrayList<CommunityVO> cmlist;
    ArrayList<SettingVO> slist;
    ArrayList<StatusVO> stlist;
    ArrayList<UserVO> ulist;
    ArrayList<MyhomeVO> hlist;

    NoticeAdapter nadapter;
    ComplainAdapter cpadapter;
    CommunityAdapter cmadapter;
    SettingAdapter sadapter;
    UserAdapter uadapter;
    StatusAdapter stadapter;
    MyhomeAdapter hadapter;

    ProgressDialog progressDialog;
    int controller;

    String id;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (controller == 1){ nlist.clear(); }
        else if (controller == 2){ cplist.clear(); }
        else if (controller == 3){ cmlist.clear(); }
        else if (controller == 4){ slist.clear(); }
        else if (controller == 5){ ulist.clear(); }
        else if (controller == 6){ stlist.clear(); }
        else if (controller == 7){ hlist.clear(); }
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (controller == 1){ nadapter.notifyDataSetChanged(); }
        else if (controller == 2){ cpadapter.notifyDataSetChanged(); }
        else if (controller == 3){ cmadapter.notifyDataSetChanged(); }
        else if (controller == 4){ sadapter.notifyDataSetChanged(); }
        else if (controller == 5){ uadapter.notifyDataSetChanged(); }
        else if (controller == 6){ stadapter.notifyDataSetChanged(); }
        else if (controller == 7){ hadapter.notifyDataSetChanged(); }
    }

    public Select(ArrayList<NoticeVO> nlist, NoticeAdapter adapter){
        this.nlist = nlist;
        this.nadapter = adapter;
        this.controller = 1;
    }
    public Select(ArrayList<ComplainVO> cplist, ComplainAdapter adapter){
        this.cplist = cplist;
        this.cpadapter = adapter;
        this.controller = 2;
    }
    public Select(ArrayList<CommunityVO> cmlist, CommunityAdapter adapter){
        this.cmlist = cmlist;
        this.cmadapter = adapter;
        this.controller = 3;
    }
    public Select(ArrayList<SettingVO> slist, SettingAdapter adapter){
        this.slist = slist;
        this.sadapter = adapter;
        this.controller = 4;
    }
    public Select(ArrayList<StatusVO> stlist, StatusAdapter adapter){
        this.stlist = stlist;
        this.stadapter = adapter;
        this.controller = 6;
    }
    public Select(ArrayList<UserVO> ulist, UserAdapter adapter){
        this.ulist = ulist;
        this.uadapter = adapter;
        this.controller = 5;
    }
    public Select(ArrayList<MyhomeVO> hlist, MyhomeAdapter adapter, String id){
        this.hlist = hlist;
        this.hadapter = adapter;
        this.controller = 7;
        this.id = id;
    }




    //execute()
    @Override
    protected Void doInBackground(Void... voids) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        HttpEntity httpEntity = null;

        String result = "";
        String postURL = "";
        if(controller == 1){
            postURL = ipConfig + "/AA/nselect";
        } else if(controller == 2){
            postURL = ipConfig + "/AA/cpselect";
        } else if(controller == 3){
            postURL = ipConfig + "/AA/cmselect";
        } else if(controller == 4){
            postURL = ipConfig + "/AA/sselect";
        } else if(controller == 5){
            postURL = ipConfig + "/AA/uselect";
        } else if(controller == 6){
            postURL = ipConfig + "/AA/stselect";
        } else if(controller == 7){
            postURL = ipConfig + "/AA/hselect?id=" + id;
        }


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

    private void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();


                if(controller == 1) {
                    while (reader.hasNext()){
                        nlist.add(new ReadMessage().noticeReadMessage(reader));
                    }
                }else if(controller == 2) {
                    while (reader.hasNext()) {
                        cplist.add(new ReadMessage().complainReadMessage(reader));
                    }
                }
                else if(controller == 3) {
                    while (reader.hasNext()) {
                        cmlist.add(new ReadMessage().communityReadMessage(reader));
                    }
                }
                else if(controller == 4) {
                    while (reader.hasNext()) {
                        slist.add(new ReadMessage().settingReadMessage(reader));
                    }
                }
                else if(controller == 5) {
                    while (reader.hasNext()) {
                        ulist.add(new ReadMessage().userReadMessage(reader));
                    }
                }
                else if(controller == 6) {
                    while (reader.hasNext()) {
                        stlist.add(new ReadMessage().statusReadMessage(reader));
                    }
                }
                else if(controller == 7) {
                    while (reader.hasNext()) {
                        hlist.add(new ReadMessage().homeReadMessage(reader));
                    }
                }
        }finally {
            reader.close();
        }
    }
}
