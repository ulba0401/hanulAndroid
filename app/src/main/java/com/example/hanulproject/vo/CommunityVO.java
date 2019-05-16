package com.example.hanulproject.vo;

import java.io.Serializable;

public class CommunityVO implements Serializable {
    int no, readcnt;
    String title, content,writer, filename, filepath, uploadType;
    String isdel;

    public CommunityVO () {}

    public CommunityVO(int no, String title, String content, String writer, String filename, String filepath, int readcnt) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.filename = filename;
        this.filepath = filepath;
        this.readcnt = readcnt;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
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

    public int getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(int readcnt) {
        this.readcnt = readcnt;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }
}
