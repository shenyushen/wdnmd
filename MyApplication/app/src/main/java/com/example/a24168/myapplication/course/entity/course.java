package com.example.a24168.myapplication.course.entity;

/**
 * @author SIONON
 * @description:
 * @date :2020/5/1 20:22
 */
public class course {
    private int course_id;                  //课程id
    private String time;                   //课程上传时间
    private String mv_name;               //课程名字
    private String mv_account;            //课程描述
    private String mv_path;               //课程路径
    private String mv_up;                 //视频作者
    public int getCourse_id() {
        return course_id;
    }

    public String getTime() {
        return time;
    }

    public String getMv_name() {
        return mv_name;
    }

    public String getMv_account() {
        return mv_account;
    }

    public String getMv_path() {
        return mv_path;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMv_name(String mv_name) {
        this.mv_name = mv_name;
    }

    public void setMv_account(String mv_account) {
        this.mv_account = mv_account;
    }

    public void setMv_path(String mv_path) {
        this.mv_path = mv_path;
    }

    public String getMv_up() {
        return mv_up;
    }

    public void setMv_up(String mv_up) {
        this.mv_up = mv_up;
    }

    public course(int course_id, String time, String mv_name, String mv_account, String mv_path, String mv_up) {
        this.course_id = course_id;
        this.time = time;
        this.mv_name = mv_name;
        this.mv_account = mv_account;
        this.mv_path = mv_path;
        this.mv_up = mv_up;
    }

    @Override
    public String toString() {
        return "course{" +
                "course_id=" + course_id +
                ", time='" + time + '\'' +
                ", mv_name='" + mv_name + '\'' +
                ", mv_account='" + mv_account + '\'' +
                ", mv_path='" + mv_path + '\'' +
                ", mv_up='" + mv_up + '\'' +
                '}';
    }
}
