package com.dc.domain;

import java.io.Serializable;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/20 14:27
 */
public class TeacherChoice implements Serializable {

    private StudentInfo studentInfo;  // 学生信息
    private TeacherProject teacherProject; // 项目信息
    private String volunteer;   // 第几志愿
    private String choice; // 选择情况

    @Override
    public String toString() {
        return "TeacherChoice{" +
                "studentInfo=" + studentInfo +
                ", teacherProject=" + teacherProject +
                ", volunteer='" + volunteer + '\'' +
                ", choice='" + choice + '\'' +
                '}';
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public TeacherProject getTeacherProject() {
        return teacherProject;
    }

    public void setTeacherProject(TeacherProject teacherProject) {
        this.teacherProject = teacherProject;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
