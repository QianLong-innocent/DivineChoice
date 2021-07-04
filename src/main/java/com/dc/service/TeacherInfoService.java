package com.dc.service;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import com.dc.domain.TeacherInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:26
 */
public interface TeacherInfoService {
    /**
     * 登录教师用户
     * @param id  工号
     * @param password 密码
     * @return 登录成功与失败
     */
    TeacherInfo loginByPrimaryKeyWithPassword(String id, String password);

    /**
     * 修改密码
     * @param id  工号
     * @param password 新密码
     * @param passwordBefore 旧密码
     * @return
     */
    int updateByPrimaryKeyChangePassword(String id,String password,String passwordBefore);

    /**
     * 修改个人信息
     * @param teacherInfo
     * @return
     */
    int updateByPrimaryKeyChangeMessage(TeacherInfo teacherInfo);

    /**
     * 查询所有教师
     * @return
     */
    List<TeacherInfo> selectAllTeacher();

    /**
     * 查询部分教师  模糊查询
     * @param name
     * @return
     */
    List<TeacherInfo> selectTeacherByName(String name);

    int updateTeacherInfo(TeacherInfo teacherInfo);

    Boolean insertTeacherInfo(TeacherInfo teacherInfo);
}
