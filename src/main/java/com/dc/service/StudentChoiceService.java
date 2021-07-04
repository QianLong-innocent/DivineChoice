package com.dc.service;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentChoiceWithTeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/14 16:32
 */
public interface StudentChoiceService {


    /**
     * 查询学生的选题情况  选择机会 + 选题志愿
     * @param student_id
     * @return
     */
    StudentChoice selectStudentChoice(String student_id);

    /**
     * 第一志愿
     * @param student_id
     * @param project_one_name
     * @return
     */
    int updateChoiceProjectOne(String student_id,String project_one_name,StudentChoice studentChoice);

    /**
     * 第二志愿
     * @param student_id
     * @param project_two_name
     * @return
     */
    int updateChoiceProjectTwo(String student_id,String project_two_name,StudentChoice studentChoice);


    /**
     * 工厂
     * @param id  学号
     * @param name  项目名
     * @param volunteer 第几志愿
     * @return
     */
    int updateChoiceProject(String id,String name,String volunteer);

    /**
     * 根据学号查询出所选的题目 + 老师基本信息
     * @param id
     * @return
     */
    List<StudentChoiceWithTeacherInfo> selectChoiceStateAndTeacherInfo(String id);

    /**
     * 在老师还没有选之前，可退选题目
     * @param id
     * @param volunteer
     * @return
     */
    int deleteChoiceProject(String id,String volunteer);


}
