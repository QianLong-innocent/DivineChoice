package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 15:00
 */
public class StudentProject implements Serializable {
    private String student_id;
    private String name;
    private String project_id;

    @Override
    public String toString() {
        return "StudentProject{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", project_id='" + project_id + '\'' +
                '}';
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
}
