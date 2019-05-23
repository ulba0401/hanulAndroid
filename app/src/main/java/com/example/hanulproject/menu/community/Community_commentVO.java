package com.example.hanulproject.menu.community;

public class Community_commentVO {
    int no, comu_no;
    String content, writer;

    public Community_commentVO(){}

    public Community_commentVO(int no, int comu_no, String content, String writer){
        this.no = no;
        this.comu_no = comu_no;
        this.content = content;
        this.writer = writer;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getComu_no() {
        return comu_no;
    }

    public void setComu_no(int comu_no) {
        this.comu_no = comu_no;
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
}
