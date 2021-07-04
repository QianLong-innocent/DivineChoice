package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 14:57
 */
public class ProjectType implements Serializable {
    private int id;
    private String project_type;

    @Override
    public String toString() {
        return "ProjectType{" +
                "id=" + id +
                ", project_type='" + project_type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }
}
