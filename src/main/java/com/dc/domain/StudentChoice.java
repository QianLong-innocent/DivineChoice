package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 14:58
 */
public class StudentChoice implements Serializable {
    private String student_id;
    private int num;
    private String project_one_name;
    private String project_two_name;
    private String one_choice;
    private String two_choice;

    @Override
    public String toString() {
        return "StudentChoice{" +
                "student_id='" + student_id + '\'' +
                ", num=" + num +
                ", project_one_name='" + project_one_name + '\'' +
                ", project_two_name='" + project_two_name + '\'' +
                ", one_choice='" + one_choice + '\'' +
                ", two_choice='" + two_choice + '\'' +
                '}';
    }

    public String getTwo_choice() {
        return two_choice;
    }

    public void setTwo_choice(String two_choice) {
        this.two_choice = two_choice;
    }

    public String getOne_choice() {
        return one_choice;
    }

    public void setOne_choice(String one_choice) {
        this.one_choice = one_choice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProject_one_name() {
        return project_one_name;
    }

    public void setProject_one_name(String project_one_name) {
        this.project_one_name = project_one_name;
    }

    public String getProject_two_name() {
        return project_two_name;
    }

    public void setProject_two_name(String project_two_name) {
        this.project_two_name = project_two_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
