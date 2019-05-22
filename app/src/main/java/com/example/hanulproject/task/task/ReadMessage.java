package com.example.hanulproject.task.task;

import android.util.JsonReader;
import android.util.Log;

import com.example.hanulproject.vo.CommunityVO;
import com.example.hanulproject.vo.ComplainVO;
import com.example.hanulproject.vo.MyhomeVO;
import com.example.hanulproject.vo.NoticeVO;
import com.example.hanulproject.vo.SettingVO;
import com.example.hanulproject.vo.StatusVO;
import com.example.hanulproject.vo.UserVO;

import java.io.IOException;

public class ReadMessage {

    public NoticeVO noticeReadMessage(JsonReader reader) throws IOException{
        int no = 0, readcnt = 0;
        String title="", content="", writedate="", filename="", filepath="";

        reader.beginObject();
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

    public ComplainVO complainReadMessage(JsonReader reader) throws IOException{
        int no = 0;
        String title="", content="", writer="", filename="", filepath="";

        reader.beginObject();
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

    public CommunityVO communityReadMessage(JsonReader reader) throws IOException{
        int no = 0, readcnt = 0;
        String title="", content="", writer="", filename="", filepath="";

        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("no")){
                no = reader.nextInt();
            }else if (readStr.equals("title")){
                title = reader.nextString();
            }else if (readStr.equals("content")){
                content = reader.nextString();
            }else if (readStr.equals("writer")){
                writer = reader.nextString();
            }else if (readStr.equals("readcnt")){
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

        return new CommunityVO(no,title,content,writer, filename,filepath, readcnt);
    }

    public MyhomeVO homeReadMessage(JsonReader reader) throws IOException{
        String id="", addr="";
        double nx=0, ny=0;
        reader.beginObject();

        while (reader.hasNext()){
            String readStr = reader.nextName();
            if (readStr.equals("id")){
                id = reader.nextString();
            } else if (readStr.equals("addr")){
                addr = reader.nextString();
            } else if (readStr.equals("nx")){
                nx = reader.nextDouble();
            } else if (readStr.equals("ny")){
                ny = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        int tmpX = (int)nx;
        int tmpy = (int)ny;

        return new MyhomeVO(id, addr, tmpX, tmpy);
    }

    public UserVO userReadMessage(JsonReader reader) throws IOException{
        String id="", pw="", name="", addr="", phone="", email="", profile="", admin="", profileName="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("id")) {
                id = reader.nextString();
            } else if (readStr.equals("pw")) {
                pw = reader.nextString();
            } else if (readStr.equals("name")) {
                name = reader.nextString();
            } else if (readStr.equals("addr")) {
                addr = reader.nextString();
            } else if (readStr.equals("phone")) {
                phone = reader.nextString();
            } else if (readStr.equals("email")) {
                email = reader.nextString();
            } else if (readStr.equals("profile")) {
                profile = reader.nextString();
            } else if (readStr.equals("admin")) {
                admin = reader.nextString();
            } else if (readStr.equals("profileName")) {
                profileName = reader.nextString();
            }else {
                reader.skipValue();
            }
        }

        reader.endObject();

        return new UserVO(id, pw, name, addr, phone, email, profile, admin, profileName);
    }

    public SettingVO settingReadMessage(JsonReader reader) throws IOException {

        return null;
    }

    public StatusVO statusReadMessage(JsonReader reader) throws IOException {

        return null;
    }


}
