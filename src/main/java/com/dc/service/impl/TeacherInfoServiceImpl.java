package com.dc.service.impl;

import com.dc.domain.StudentInfo;
import com.dc.domain.TeacherInfo;
import com.dc.repository.StudentInfoRepository;
import com.dc.repository.TeacherInfoRepository;
import com.dc.service.TeacherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:27
 */
@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    private static final Logger log = LoggerFactory.getLogger(TeacherInfoServiceImpl.class);

    // 数据访问层进行操作数据库
    // 业务层调用数据持久层
    @Resource
    private TeacherInfoRepository teacherInfoRepository;


    @Override
    public TeacherInfo loginByPrimaryKeyWithPassword(String id, String password) {
        // 登录成功与否。由于要在前端展示响应的数据。因此需要返回整个数据
        // 前端的一个token值
        TeacherInfo teacherInfo = teacherInfoRepository.selectByPrimaryKeyWithPassword(id,password);
        return teacherInfo;
    }

    @Override
    @Transactional
    public int updateByPrimaryKeyChangePassword(String id, String password,String passwordBefore) {
        return teacherInfoRepository.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeyChangeMessage(TeacherInfo teacherInfo) {
        return teacherInfoRepository.updateByPrimaryKeyChangeMessage(teacherInfo);
    }

    @Override
    public List<TeacherInfo> selectAllTeacher() {
        return teacherInfoRepository.selectAllTeacher();
    }

    @Override
    public List<TeacherInfo> selectTeacherByName(String name) {
        return teacherInfoRepository.selectTeacherByName(name);
    }

    @Override
    public int updateTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoRepository.updateTeacherInfo(teacherInfo);
    }

    @Override
    @Transactional
    public Boolean insertTeacherInfo(TeacherInfo teacherInfo) {
        TeacherInfo teacherInfo1 = teacherInfoRepository.selectTeacherInfo(teacherInfo);
        if(teacherInfo1 != null)
            return false;

        teacherInfoRepository.insertTeacherInfo(teacherInfo);
        return true;
    }
}
