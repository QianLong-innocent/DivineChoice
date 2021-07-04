package com.dc.service;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:25
 */
public interface StudentInfoService {

    /**
     * 登录学生用户
     * @param id  学号
     * @param password 密码
     * @return 登录成功与失败
     */
    StudentInfo loginByPrimaryKeyWithPassword(String id,String password);


    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    int updateByPrimaryKeyChangePassword(String id,String password,String passwordBefore);

    /**
     * 修改个人信息
     * @param studentInfo
     * @return
     */
    int updateByPrimaryKeyChangeMessage(StudentInfo studentInfo);

    /**
     * 查询所有学生
     * @return
     */
    List<StudentInfoFinal> selectAllStudent();

    /**
     * 查询部分学生  模糊查询
     * @param name
     * @return
     */
    List<StudentInfoFinal> selectStudentByName(String name);

    int updateStudentInfo(StudentInfo studentInfo);

    Boolean insertStudent(StudentInfoFinal studentInfoFinal);
}
