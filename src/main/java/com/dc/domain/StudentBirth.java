package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 14:58
 */
public class StudentBirth implements Serializable {
    private String student_id;
    private String department;
    private String professional;

    @Override
    public String toString() {
        return "StudentBirth{" +
                "student_id='" + student_id + '\'' +
                ", department='" + department + '\'' +
                ", professional='" + professional + '\'' +
                '}';
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }
}
