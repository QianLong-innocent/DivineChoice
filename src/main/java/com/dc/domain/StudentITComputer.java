package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 14:59
 */
public class StudentITComputer implements Serializable {
    private int id;
    private String project_name;
    private String project_type;
    private String project_source;
    private String project_content;
    private String num;

    @Override
    public String toString() {
        return "StudentITComputer{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", project_type='" + project_type + '\'' +
                ", project_source='" + project_source + '\'' +
                ", project_content='" + project_content + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_source() {
        return project_source;
    }

    public void setProject_source(String project_source) {
        this.project_source = project_source;
    }

    public String getProject_content() {
        return project_content;
    }

    public void setProject_content(String project_content) {
        this.project_content = project_content;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
