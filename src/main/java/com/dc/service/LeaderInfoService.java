package com.dc.service;

import com.dc.domain.LeaderInfo;
import com.dc.domain.StudentInfo;
import com.dc.domain.TeacherInfo;

import java.util.Map;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:26
 */
public interface LeaderInfoService {



    /**
     * 登录主任用户
     * @param id  工号
     * @param password 密码
     * @return 登录成功与失败
     */
    LeaderInfo loginByPrimaryKeyWithPassword(String id, String password);

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
     * @param leaderInfo
     * @return
     */
    int updateByPrimaryKeyChangeMessage(LeaderInfo leaderInfo);


    String deleteUserChoice(Map<String,String> params);


}
