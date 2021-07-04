package com.dc.service.impl;

import com.dc.domain.*;
import com.dc.repository.LeaderInfoRepository;
import com.dc.repository.StudentBirthRepository;
import com.dc.repository.StudentChoiceRepository;
import com.dc.repository.TeacherInfoRepository;
import com.dc.service.LeaderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:27
 */
@Service
public class LeaderInfoServiceImpl implements LeaderInfoService {

    private static final Logger log = LoggerFactory.getLogger(LeaderInfoServiceImpl.class);

    // 数据访问层进行操作数据库
    // 业务层调用数据持久层
    @Resource
    private LeaderInfoRepository leaderInfoRepository;

    @Resource
    private StudentChoiceRepository studentChoiceRepository;

    @Resource
    private StudentBirthRepository studentBirthRepository;

    @Override
    public LeaderInfo loginByPrimaryKeyWithPassword(String id, String password) {
        // 登录成功与否。由于要在前端展示响应的数据。因此需要返回整个数据
        // 前端的一个token值
        LeaderInfo leaderInfo = leaderInfoRepository.selectByPrimaryKeyWithPassword(id,password);
        return leaderInfo;
    }

    @Override
    public int updateByPrimaryKeyChangePassword(String id, String password, String passwordBefore) {
        return leaderInfoRepository.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
    }

    @Override
    public int updateByPrimaryKeyChangeMessage(LeaderInfo leaderInfo) {
        return leaderInfoRepository.updateByPrimaryKeyChangeMessage(leaderInfo);
    }

    @Override
    @Transactional
    public String deleteUserChoice(Map<String, String> params) {

        // 1. 先去student_choice表 找是否已经选择
        StudentChoice studentChoice = studentChoiceRepository.selectStudentChoice(params.get("student_id"));
        String one = studentChoice.getOne_choice();
        String two = studentChoice.getTwo_choice();
        // 2. 如果未被选择，退题失败。如果被选择，执行清理数据库表

        if(one.equals("已选择") || two.equals("已选择")){
            String answer = "";
            if(one.equals("已选择")){
                answer = studentChoice.getProject_one_name();
                studentChoice.setProject_one_name("");
                studentChoice.setOne_choice("待选");
            }else{
                answer = studentChoice.getProject_two_name();
                studentChoice.setProject_two_name("");
                studentChoice.setTwo_choice("待选");
            }

            // 2.1 清理student_choice
            leaderInfoRepository.updateStudentChoice(studentChoice);

            // 2.2 清理student_project
            leaderInfoRepository.updateStudentProject(studentChoice.getStudent_id());

            // 2.3 清理teacher_project
            leaderInfoRepository.updateTeacherProject(answer);

            // 2.4 清理对应专业的
            String birth = studentBirthRepository.selectProfessional(studentChoice.getStudent_id());
            if("计科".equals(birth)){
                leaderInfoRepository.updateStudentITComputer(answer);
            }else{
                leaderInfoRepository.updateStudentITSoftwareengineering(answer);
            }

            return "退题成功！";
        }else{
            return "该学生，可自行退题！";
        }









    }
}
