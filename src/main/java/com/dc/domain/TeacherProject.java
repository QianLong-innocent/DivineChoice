package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 15:00
 */
public class TeacherProject implements Serializable {
    private String name;
    private String student_id;
    private String project_id;
    private String project_name;
    private String project_content;
    private String professional;
    private String num;

    @Override
    public String toString() {
        return "TeacherProject{" +
                "name='" + name + '\'' +
                ", student_id='" + student_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", project_content='" + project_content + '\'' +
                ", professional='" + professional + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_content() {
        return project_content;
    }

    public void setProject_content(String project_content) {
        this.project_content = project_content;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
