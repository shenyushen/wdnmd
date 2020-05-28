package com.example.a24168.myapplication.course.entity;

import java.io.Serializable;

/**
 * @author SIONON
 * @description:
 * @date :2020/5/21 21:06
 */
public class comment implements Serializable {
    private String author;       //评论作者
    private String content;    //评论内容
    private String time;  //图片路径
    private String mv_id;   //评论标志：属于那个视频A

    public comment(String author, String content, String time, String mv_id) {
        this.author = author;
        this.content = content;
        this.time = time;
        this.mv_id = mv_id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getMv_id() {
        return mv_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMv_id(String mv_id) {
        this.mv_id = mv_id;
    }

    @Override
    public String toString() {
        return "comment{" +
                "author=" + author +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", mv_id=" + mv_id +
                '}';
    }
}
