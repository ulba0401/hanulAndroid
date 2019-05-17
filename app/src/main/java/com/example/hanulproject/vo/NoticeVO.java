package com.example.hanulproject.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable {
    int no, readcnt;
    String title, content, writedate, filename, filepath, uploadType;
    String isdel;

    public NoticeVO () {}

    public NoticeVO(int no, String title, String content, String writedate, int readcnt, String filename, String filepath) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.writedate = writedate;
        this.readcnt = readcnt;
        this.filename = filename;
        this.filepath = filepath;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }

    public int getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(int readcnt) {
        this.readcnt = readcnt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
