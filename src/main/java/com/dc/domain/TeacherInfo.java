package com.dc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 14:57
 */
public class TeacherInfo implements Serializable {
    private String teacher_id;
    private String password;
    private String name;
    private String id_card;
    private String sex;
    private Date birthday;
    private String phone;
    private String email;
    private String remark;

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "teacher_id='" + teacher_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id_card='" + id_card + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
