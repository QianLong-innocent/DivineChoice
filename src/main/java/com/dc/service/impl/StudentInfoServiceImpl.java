package com.dc.service.impl;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import com.dc.repository.StudentChoiceRepository;
import com.dc.repository.StudentInfoRepository;
import com.dc.service.StudentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 22:25
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    private static final Logger log = LoggerFactory.getLogger(StudentInfoServiceImpl.class);

    // 数据访问层进行操作数据库
    // 业务层调用数据持久层
    @Resource
    private StudentInfoRepository studentInfoRepository;

    @Resource
    private StudentChoiceRepository studentChoiceRepository;
    @Override
    @Transactional
    public StudentInfo loginByPrimaryKeyWithPassword(String id, String password) {

        // 登录成功与否。由于要在前端展示响应的数据。因此需要返回整个数据
        // 前端的一个token值
        StudentInfo studentInfo = studentInfoRepository.selectByPrimaryKeyWithPassword(id,password);
        return studentInfo;

    }

    @Override
    @Transactional
    public int updateByPrimaryKeyChangePassword(String id, String password,String passwordBefore) {
        return studentInfoRepository.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeyChangeMessage(StudentInfo studentInfo) {
        return studentInfoRepository.updateByPrimaryKeyChangeMessage(studentInfo);
    }

    @Override
    public List<StudentInfoFinal> selectAllStudent() {
        List<StudentInfoFinal> list = studentInfoRepository.selectAllStudent();
        List<StudentChoice> studentChoices = studentChoiceRepository.selectAllStudentChoice();

        for(StudentInfoFinal i : list){
            for(StudentChoice j : studentChoices){
                if(i.getStudent_id().equals(j.getStudent_id())){
                    if("已选择".equals(j.getOne_choice())){
                        i.setProject(j.getProject_one_name());
                        break;
                    }
                    if("已选择".equals(j.getTwo_choice())){
                        i.setProject(j.getProject_two_name());
                        break;
                    }
                }
            }
            if(i.getProject() == null){
                i.setProject("暂时无");
            }
        }
        return list;
    }

    @Override
    public List<StudentInfoFinal> selectStudentByName(String name) {
        return studentInfoRepository.selectStudentByName(name);
    }

    @Override
    @Transactional
    public int updateStudentInfo(StudentInfo studentInfo) {
//        System.out.println(studentInfo);
        return studentInfoRepository.updateStudentInfo(studentInfo);
    }

    @Override
    @Transactional
    public Boolean insertStudent(StudentInfoFinal studentInfoFinal) {

        String id = studentInfoFinal.getStudent_id();
        StudentInfo studentInfo = studentInfoRepository.selectStudent(id);
        if(studentInfo != null){
            return false;
        }
        studentInfoRepository.insertStudentInfo(studentInfoFinal);
        studentInfoRepository.insertStudentBirth(studentInfoFinal);
        studentInfoRepository.insertStudentChoice(studentInfoFinal);
        studentInfoRepository.insertStudentProject(studentInfoFinal);
        return true;
    }
}
