package com.dc.service.impl;

import com.dc.domain.*;
import com.dc.repository.*;
import com.dc.service.StudentChoiceService;
import com.dc.service.TeacherProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 15:44
 */
@Service
public class TeacherProjectServiceImpl implements TeacherProjectService {

    @Resource
    TeacherProjectRepository teacherProjectRepository;

    @Resource
    StudentBirthRepository studentBirthRepository;

    @Resource
    StudentITComputerRepository studentITComputerRepository;

    @Resource
    StudentChoiceRepository studentChoiceRepository;

    @Resource
    StudentInfoRepository studentInfoRepository;

    @Resource
    StudentProjectRepository studentProjectRepository;

    public final StudentChoiceService studentChoiceService;

    public TeacherProjectServiceImpl(StudentChoiceService studentChoiceService) {
        this.studentChoiceService = studentChoiceService;
    }

    @Override
    public List<String> selectTeacherName() {
        return teacherProjectRepository.selectTeacherName();
    }

    @Override
    public List<TeacherProject> selectTeacherProject(String name, String id) {

        String professional = studentBirthRepository.selectProfessional(id);
        // 根据专业，查询出了老师的所有题目
        List<TeacherProject> teacherProjectList = teacherProjectRepository.selectTeacherProject(name,professional);


        return teacherProjectList;
    }

    @Override
    public List<TeacherProject> selectAllTeacherProject() {
        return teacherProjectRepository.selectAllTeacherProject();
    }

    @Override
    public List<TeacherProject> selectAllTeacherProjectForComputer(String teacherName) {
        return teacherProjectRepository.selectAllTeacherProjectForComputer(teacherName);
    }

    @Override
    public List<TeacherChoice> selectAllTeacherProjectForComputerForChoice(String teacherName) {

        List<TeacherChoice> listAnswer = new ArrayList<>();


        // 1. 拿到自己的项目所有
        List<TeacherProject> listTeahcerProject = teacherProjectRepository.selectAllTeacherProjectForComputer(teacherName);
        List<StudentChoice> listStudentChoice = studentChoiceRepository.selectAllStudentChoice();
//        System.out.println(listStudentChoice);
        // 2. 将项目 x2 + 查询学生选择情况
        // 一个项目 遍历 所有学生选题情况
        for(TeacherProject i : listTeahcerProject){
            int flag = 0;
            String projectName = i.getProject_name();
//            System.out.println(projectName);
            for(StudentChoice j : listStudentChoice){
                String studentOneChoice = j.getProject_one_name();
                String studentTwoChoice = j.getProject_two_name();

                // 第一志愿
                if(projectName.equals(studentOneChoice)){
                    flag++;
                    TeacherChoice teacherChoice = new TeacherChoice();
                    teacherChoice.setChoice(j.getOne_choice());
                    teacherChoice.setVolunteer("第一志愿");
                    teacherChoice.setTeacherProject(i);
                    StudentInfo studentInfo = studentInfoRepository.selectStudent(j.getStudent_id());
                    teacherChoice.setStudentInfo(studentInfo);
//                    System.out.println(studentInfo);
                    listAnswer.add(teacherChoice);
                    continue;
                }
                // 第二志愿
                if(projectName.equals(studentTwoChoice)) {
                    flag++;
                    TeacherChoice teacherChoice = new TeacherChoice();
                    teacherChoice.setChoice(j.getTwo_choice());
                    teacherChoice.setVolunteer("第二志愿");
                    teacherChoice.setTeacherProject(i);
                    StudentInfo studentInfo = studentInfoRepository.selectStudent(j.getStudent_id());
                    teacherChoice.setStudentInfo(studentInfo);
//                    System.out.println(studentInfo);
                    listAnswer.add(teacherChoice);
                    continue;
                }
            }
            // 未选择  flag < 2 手动添加一部分数据
            if(flag < 2){
                TeacherChoice teacherChoice = new TeacherChoice();
                teacherChoice.setTeacherProject(i);
                if(flag == 0) {
                    listAnswer.add(teacherChoice);
                    listAnswer.add(teacherChoice);
                } else if(flag == 1){
                    listAnswer.add(teacherChoice);
                }
            }
        }

//        System.out.println(listAnswer);

        return listAnswer;
    }

    @Override
    public List<TeacherProject> selectAllTeacherProjectForSoft(String teacherName) {
        return teacherProjectRepository.selectAllTeacherProjectForSoft(teacherName);
    }

    @Override
    public List<TeacherChoice> selectAllTeacherProjectForSoftForChoice(String teacherName) {
        List<TeacherChoice> listAnswer = new ArrayList<>();


        // 1. 拿到自己的项目所有
        List<TeacherProject> listTeahcerProject = teacherProjectRepository.selectAllTeacherProjectForSoft(teacherName);
        List<StudentChoice> listStudentChoice = studentChoiceRepository.selectAllStudentChoice();
//        System.out.println(listStudentChoice);
        // 2. 将项目 x2 + 查询学生选择情况
        // 一个项目 遍历 所有学生选题情况
        for(TeacherProject i : listTeahcerProject){
            int flag = 0;
            String projectName = i.getProject_name();
//            System.out.println(projectName);
            for(StudentChoice j : listStudentChoice){
                String studentOneChoice = j.getProject_one_name();
                String studentTwoChoice = j.getProject_two_name();

                // 第一志愿
                if(projectName.equals(studentOneChoice)){
                    flag++;
                    TeacherChoice teacherChoice = new TeacherChoice();
                    teacherChoice.setChoice(j.getOne_choice());
                    teacherChoice.setVolunteer("第一志愿");
                    teacherChoice.setTeacherProject(i);
                    StudentInfo studentInfo = studentInfoRepository.selectStudent(j.getStudent_id());
                    teacherChoice.setStudentInfo(studentInfo);
//                    System.out.println(studentInfo);
                    listAnswer.add(teacherChoice);
                    continue;
                }
                // 第二志愿
                if(projectName.equals(studentTwoChoice)) {
                    flag++;
                    TeacherChoice teacherChoice = new TeacherChoice();
                    teacherChoice.setChoice(j.getTwo_choice());
                    teacherChoice.setVolunteer("第二志愿");
                    teacherChoice.setTeacherProject(i);
                    StudentInfo studentInfo = studentInfoRepository.selectStudent(j.getStudent_id());
                    teacherChoice.setStudentInfo(studentInfo);
//                    System.out.println(studentInfo);
                    listAnswer.add(teacherChoice);
                    continue;
                }
            }
            // 未选择  flag < 2 手动添加一部分数据
            if(flag < 2){
                TeacherChoice teacherChoice = new TeacherChoice();
                teacherChoice.setTeacherProject(i);
                if(flag == 0) {
                    listAnswer.add(teacherChoice);
                    listAnswer.add(teacherChoice);
                } else if(flag == 1){
                    listAnswer.add(teacherChoice);
                }
            }
        }

//        System.out.println(listAnswer);

        return listAnswer;
    }

    @Override
    @Transactional
    public int updateProjectNameAndContent(String project_name, String project_newName, String project_content,String professional) {

        if(!project_name.equals(project_newName)){
            // 当修改了项目名之后，检查是否重复
            List<String> list = teacherProjectRepository.seleteProjectName();
            if(list.contains(project_newName)){
                return 2;
            }
        }

        // 学生选择了之后，也需要修改被选情况
        // student_choice
        List<StudentChoice> list = studentChoiceRepository.selectAllStudentChoice();

        for(StudentChoice i : list){
            if(project_name.equals(i.getProject_one_name())){
                studentChoiceRepository.updateChoiceProjectOneT(i.getStudent_id(),project_newName);
            }
            if(project_name.equals(i.getProject_two_name())){
                studentChoiceRepository.updateChoiceProjectTwoT(i.getStudent_id(),project_newName);
            }
        }



        if("selectAllTeacherProjectForComputer".equals(professional)){
            studentITComputerRepository.updateProjectForComputer(project_name,project_newName,project_content);
        }else{
            studentITComputerRepository.updateProjectForSoft(project_name,project_newName,project_content);
        }

        return teacherProjectRepository.updateProjectNameAndContent(project_name,project_newName,project_content);
    }

    @Override
    @Transactional
    public int insertProject(TeacherProject teacherProject,String project_type) {

        List<String> list = teacherProjectRepository.seleteProjectName();

        if(list.contains(teacherProject.getProject_name())){
            return 2;
        }

        StudentITComputer studentITComputer = new StudentITComputer();
        studentITComputer.setProject_content(teacherProject.getProject_content());
        studentITComputer.setProject_name(teacherProject.getProject_name());
        studentITComputer.setProject_type(project_type);
        // 大表
        if("计科".equals(teacherProject.getProfessional())){
            studentITComputerRepository.insertProjectForComputer(studentITComputer);
        }else{
            studentITComputerRepository.insertProjectForSoft(studentITComputer);
        }
        teacherProject.setProject_id(String.valueOf(studentITComputer.getId()));

        // 老师项目
        int flag  = teacherProjectRepository.insertProject(teacherProject);

        return flag;
    }

    @Override
    @Transactional
    public int deleteProject(String project_id,String professional,String project_name) {


        // 学生选择了之后，也需要删除被选情况
        // student_choice
        List<StudentChoice> list = studentChoiceRepository.selectAllStudentChoice();
        List<String> listIdOne = new ArrayList<>();
        List<String> listIdTwo = new ArrayList<>();
        for(StudentChoice i : list){
            if(project_name.equals(i.getProject_one_name())){
                if(!i.getOne_choice().equals("待选"))
                    return -1;
                listIdOne.add(i.getStudent_id());
            }
            if(project_name.equals(i.getProject_two_name())){
                if(!i.getTwo_choice().equals("待选"))
                    return -1;
                listIdTwo.add(i.getStudent_id());
            }
        }

        for(String i : listIdOne){
            studentChoiceRepository.deleteChoiceProjectOne(i);
        }
        for(String i : listIdTwo){
            studentChoiceRepository.deleteChoiceProjectTwo(i);
        }


        if("计科".equals(professional)){
            studentITComputerRepository.deleteProjectForComputer(project_id,project_name);
        }else{
            studentITComputerRepository.deleteProjectForSoft(project_id,project_name);
        }

        return teacherProjectRepository.deleteProject(project_id,project_name);
    }

    @Override
    @Transactional
    public int choiceStudent(TeacherChoice teacherChoice) {

        if(teacherChoice.getChoice().equals("已选择")){
            return 2;
        }

        String id = teacherChoice.getStudentInfo().getStudent_id();
        StudentChoice studentChoice = studentChoiceRepository.selectStudentChoice(id);
        // 1. 将这个变成已选择。
        // 2. 退选另外一个志愿。归还使用权
        if("第一志愿".equals(teacherChoice.getVolunteer())){
            studentChoiceRepository.updateChoiceOne(id);
            if(!"".equals(studentChoice.getProject_two_name()))
                studentChoiceService.deleteChoiceProject(id,"第二志愿");
        }else{
            studentChoiceRepository.updateChoiceTwo(id);
            if(!"".equals(studentChoice.getProject_one_name()))
                studentChoiceService.deleteChoiceProject(id,"第一志愿");
        }

        // 3. 操作数据库将已选择的这个项目 两张表num   变成 2
        // 3. 如果有另外的人选了该题，直接清除
        String projectName = teacherChoice.getTeacherProject().getProject_name();
        List<StudentChoice> list = studentChoiceRepository.selectAllStudentChoice();
        for(StudentChoice i : list){
            if(!i.getStudent_id().equals(id)) {
                if (projectName.equals(i.getProject_one_name())) {
                    studentChoiceRepository.deleteChoiceProjectOne(i.getStudent_id());
                }
                if (projectName.equals(i.getProject_two_name())) {
                    studentChoiceRepository.deleteChoiceProjectTwo(i.getStudent_id());
                }
            }
        }

        teacherProjectRepository.updateTeacherProjectNumWithProjectName(projectName);
        if("计科".equals(teacherChoice.getTeacherProject().getProfessional())){
            teacherProjectRepository.updateComputerNumWithProjectName(projectName);
        }else{
            teacherProjectRepository.updateSoftNumWithProjectName(projectName);
        }


        // 4. 更新student_project
        StudentProject studentProject = new StudentProject();
        studentProject.setStudent_id(id);
        studentProject.setName(teacherChoice.getTeacherProject().getName());
        studentProject.setProject_id(teacherChoice.getTeacherProject().getProject_id());
        studentProjectRepository.updateStudentProject(studentProject);

        return 0;
    }


}
