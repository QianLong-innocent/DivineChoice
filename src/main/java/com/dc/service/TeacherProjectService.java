package com.dc.service;

import com.dc.domain.TeacherChoice;
import com.dc.domain.TeacherProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 15:43
 */
public interface TeacherProjectService {

    /**
     * 查询所有老师姓名
     * @return 所有老师姓名
     */
    List<String> selectTeacherName();

    /**
     * 根据学生相应专业，查询老师所有的毕设项目。
     * @param name 老师名字
     * @param professional 学生专业
     * @return 毕设项目
     */
    List<TeacherProject> selectTeacherProject(String name,String professional);


    /**
     * 查询所有题目
     * @return
     */
    List<TeacherProject> selectAllTeacherProject();

    /**
     * 查询所有计科题目
     * @return
     */
    List<TeacherProject> selectAllTeacherProjectForComputer(String teacherName);
    List<TeacherChoice> selectAllTeacherProjectForComputerForChoice(String teacherName);

    /**
     * 查询所有软工题目
     * @return
     */
    List<TeacherProject> selectAllTeacherProjectForSoft(String teacherName);
    List<TeacherChoice> selectAllTeacherProjectForSoftForChoice(String teacherName);



    /**
     * 更新教师项目名称 + 内容
     * @param project_name
     * @param project_newName
     * @param project_content
     * @param professional
     * @return
     */
    int updateProjectNameAndContent(String project_name, String project_newName, String project_content,String professional);

    /**
     * 添加项目
     * @param teacherProject
     * @param project_type
     * @return
     */
    int insertProject(TeacherProject teacherProject,String project_type);

    /**
     * 删除项目
     * @param project_id
     * @param professional
     * @param project_name
     * @return
     */
    int deleteProject(String project_id,String professional,String project_name);

    /**
     * 选择学生
     * @param teacherChoice
     * @return
     */
    int choiceStudent(TeacherChoice teacherChoice);

}
