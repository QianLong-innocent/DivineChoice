package com.dc.service.impl;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentChoiceWithTeacherInfo;
import com.dc.domain.TeacherInfo;
import com.dc.domain.TeacherProject;
import com.dc.repository.*;
import com.dc.service.StudentChoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/14 16:35
 */
@Service
public class StudentChoiceServiceImpl implements StudentChoiceService {

    @Resource
    StudentChoiceRepository studentChoiceRepository;

    @Resource
    TeacherProjectRepository teacherProjectRepository;

    @Resource
    TeacherInfoRepository teacherInfoRepository;

    @Resource
    StudentBirthRepository studentBirthRepository;

    @Resource
    ProjectTypeRespository projectTypeRespository;


    @Override
    public StudentChoice selectStudentChoice(String student_id) {
        return studentChoiceRepository.selectStudentChoice(student_id);
    }

    @Override
    @Transactional
    public int updateChoiceProjectOne(String student_id, String project_one_name,StudentChoice studentChoice) {

        if(studentChoice.getProject_one_name() == null || "".equals(studentChoice.getProject_one_name())){

            updateStudent_IT_xxx(student_id,project_one_name);

            studentChoiceRepository.updateChoiceProjectOne(student_id,project_one_name);
            return 1;
        }

        return 0;
    }

    @Override
    @Transactional
    public int updateChoiceProjectTwo(String student_id, String project_two_name,StudentChoice studentChoice) {

        if(studentChoice.getProject_two_name() == null || "".equals(studentChoice.getProject_two_name())){
            // 根据专业查询两个表，让num++
            updateStudent_IT_xxx(student_id,project_two_name);
            studentChoiceRepository.updateChoiceProjectTwo(student_id,project_two_name);
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int updateChoiceProject(String id, String name, String volunteer) {
        StudentChoice studentChoice = studentChoiceRepository.selectStudentChoice(id);
        if(studentChoice.getNum() == 2){
            return 2;
        }
        String oneChoice = studentChoice.getOne_choice();
        String twoChoice = studentChoice.getTwo_choice();
        if(!"待选".equals(oneChoice) || !"待选".equals(twoChoice)){
            return 4;
        }

        if("1".equals(volunteer)){
            if(name.equals(studentChoice.getProject_two_name())){
                return 3;
            }
            return updateChoiceProjectOne(id,name,studentChoice);
        }else{

            if(name.equals(studentChoice.getProject_one_name())){
                return 3;
            }
            return updateChoiceProjectTwo(id,name,studentChoice);
        }
    }

    @Override
    @Transactional
    public List<StudentChoiceWithTeacherInfo> selectChoiceStateAndTeacherInfo(String id) {

        // 最终
        List<StudentChoiceWithTeacherInfo> list = new ArrayList<>();
        StudentChoiceWithTeacherInfo jsonContentOne = new StudentChoiceWithTeacherInfo();
        StudentChoiceWithTeacherInfo jsonContentTwo = new StudentChoiceWithTeacherInfo();
        // 1. 根据学号查出选题 名称 + 状态
        StudentChoice studentChoice = studentChoiceRepository.selectStudentChoice(id);
        if(studentChoice.getNum() == 0){
            return null;
        }
        String one = studentChoice.getProject_one_name();
        String two = studentChoice.getProject_two_name();
        if(one == null || "".equals(one)){
            ;
        }else{

            jsonContentOne.setIndex("1");
            jsonContentOne.setProject_name(studentChoice.getProject_one_name());
            jsonContentOne.setState(studentChoice.getOne_choice());
            jsonContentOne.setVolunteer("第一志愿");
            selectProjectAndTeacher(jsonContentOne,one);
            list.add(jsonContentOne);
        }
        if(two == null || "".equals(two)){
            ;
        }else{
            jsonContentTwo.setIndex("2");
            jsonContentTwo.setProject_name(studentChoice.getProject_two_name());
            jsonContentTwo.setState(studentChoice.getTwo_choice());
            jsonContentTwo.setVolunteer("第二志愿");
            selectProjectAndTeacher(jsonContentTwo,two);
            list.add(jsonContentTwo);
        }
        return list;
    }

    @Override
    @Transactional
    public int deleteChoiceProject(String id,String volunteer) {

        // 1. 根据学号 查出 选的题
        StudentChoice studentChoice = studentChoiceRepository.selectStudentChoice(id);



        if("第一志愿".equals(volunteer)){
            if(!studentChoice.getOne_choice().equals("待选")){
                return 10;
            }
            String project_name = studentChoice.getProject_one_name();
            // 2. 根据选的题，将大表需要知道专业的num--  老师的num--
            deleteStudent_IT_xxx(id,project_name);

            return studentChoiceRepository.deleteChoiceProjectOne(id);
        }else{
            if(!studentChoice.getTwo_choice().equals("待选")){
                return 10;
            }
            String project_name = studentChoice.getProject_two_name();
            // 2. 根据选的题，将大表的num--  老师的num--
            deleteStudent_IT_xxx(id,project_name);
            return studentChoiceRepository.deleteChoiceProjectTwo(id);
        }

    }

    public void selectProjectAndTeacher(StudentChoiceWithTeacherInfo info,String projectName){
        // 2. 根据项目名查出 项目编号 + 老师 + 项目内容
        TeacherProject teacherProject = teacherProjectRepository.selectTeacherProjectWithProjectName(projectName);

        info.setProject_id(teacherProject.getProject_id());
        info.setTeacherName(teacherProject.getName());
        info.setProject_content(teacherProject.getProject_content());
        // 3. 根据老师名查出 手机号
        List<String> list = teacherInfoRepository.selectPhoneByTeacherName(teacherProject.getName());
        info.setPhone(list.get(0));
    }

    // 让选题表的num++
    @Transactional
    public void updateStudent_IT_xxx(String id,String project_name){
        // 根据专业查询两个表，让num++
        String pro = studentBirthRepository.selectProfessional(id);
        // 教师表
        teacherProjectRepository.updateTeacherProjectNum(project_name);
        // 大表
        if("计科".equals(pro)){
            projectTypeRespository.updateStudentITComputer(project_name);
        }else{
            projectTypeRespository.updateStudentITSoftwareengineering(project_name);
        }
    }

    @Transactional
    public void deleteStudent_IT_xxx(String id,String project_name){
        // 根据专业查询两个表，让num++
        String pro = studentBirthRepository.selectProfessional(id);
        // 教师表
        teacherProjectRepository.deleteTeacherProjectNum(project_name);
        // 大表
        if("计科".equals(pro)){
            projectTypeRespository.deleteStudentITComputer(project_name);
        }else{
            projectTypeRespository.deleteStudentITSoftwareengineering(project_name);
        }
    }

}
