package com.example.hanulproject.vo;

import java.io.Serializable;

public class ComplainVO implements Serializable {
    int no;
    String title, content,writer, filename, filepath, admin;
    String isdel;

    public ComplainVO () {}

    public ComplainVO(int no, String title, String content, String writer, String filename, String filepath) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.filename = filename;
        this.filepath = filepath;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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
