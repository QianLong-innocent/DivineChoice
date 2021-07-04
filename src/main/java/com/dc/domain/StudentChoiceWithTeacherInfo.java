package com.dc.domain;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/15 16:07
 */
public class StudentChoiceWithTeacherInfo {
    private String index;
    private String project_name;
    private String state;
    private String volunteer;
    private String project_id;
    private String teacherName;
    private String project_content;
    private String phone;

    @Override
    public String toString() {
        return "StudentChoiceWithTeacherInfo{" +
                "index='" + index + '\'' +
                ", project_name='" + project_name + '\'' +
                ", state='" + state + '\'' +
                ", volunteer='" + volunteer + '\'' +
                ", project_id='" + project_id + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", project_content='" + project_content + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getProject_content() {
        return project_content;
    }

    public void setProject_content(String project_content) {
        this.project_content = project_content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
