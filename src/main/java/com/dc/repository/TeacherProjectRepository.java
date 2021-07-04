package com.dc.repository;

import com.dc.domain.TeacherProject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 15:39
 */
@Mapper
public interface TeacherProjectRepository {

    // 查询出所有的老师
    @Select({
            "SELECT name from teacher_project GROUP BY name"
    })
    List<String> selectTeacherName();


    // 根据老师姓名 + 学生专业查出自己具体可以看那些题
    // id 项目编号 项目名称 项目内容
    @Select({
            "SELECT * from teacher_project WHERE name = #{name,jdbcType=VARCHAR} " +
                    "and professional = #{professional,jdbcType=VARCHAR} and num < 2 " +
                    "ORDER BY CAST(project_id as DECIMAL)"
    })
    List<TeacherProject> selectTeacherProject(@Param("name") String name,@Param("professional") String professional);

    // 根据项目名 查询该项目的所有信息
    @Select({
            "select * from teacher_project where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    TeacherProject selectTeacherProjectWithProjectName(@Param("project_name") String project_name);

    // 更新teacherProject num + 1
    @Update({
            "update teacher_project set num = num + 1 where project_name = #{project_name}"
    })
    int updateTeacherProjectNum(String project_name);

    // 更新teacherProject num - 1
    @Update({
            "update teacher_project set num = num - 1 where project_name = #{project_name}"
    })
    int deleteTeacherProjectNum(String project_name);


    @Select({
            "select * from teacher_project"
    })
    List<TeacherProject> selectAllTeacherProject();

    @Select({
            "select * from teacher_project where professional = '计科' and name = #{teacherName,jdbcType=VARCHAR} order by project_id"
    })
    List<TeacherProject> selectAllTeacherProjectForComputer(String teacherName);

    @Select({
            "select project_name from teacher_project"
    })
    List<String> seleteProjectName();

    @Select({
            "select * from teacher_project where professional = '软工' and name = #{teacherName,jdbcType=VARCHAR} order by project_id"
    })
    List<TeacherProject> selectAllTeacherProjectForSoft(String teacherName);

    @Update({
            "update teacher_project " +
                    "set project_name = #{project_newName,jdbcType=VARCHAR},project_content = #{project_content,jdbcType=VARCHAR} " +
                    "where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateProjectNameAndContent(@Param("project_name") String project_name,@Param("project_newName") String project_newName,@Param("project_content") String project_content);

    @Insert({
        "insert into teacher_project (name,project_id,project_name,project_content,professional) " +
                "values(#{name,jdbcType=VARCHAR},#{project_id,jdbcType=VARCHAR},#{project_name,jdbcType=VARCHAR}," +
                "#{project_content,jdbcType=VARCHAR},#{professional,jdbcType=VARCHAR})"
    })
    int insertProject(TeacherProject teacherProject);


    @Delete({
            "delete from teacher_project where project_id = #{project_id,jdbcType=VARCHAR} and project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int deleteProject(@Param("project_id") String project_id,@Param("project_name") String project_name);



    @Update({
            "update teacher_project set num = 2 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateTeacherProjectNumWithProjectName(String project_name);

    @Update({
            "update student_it_computer set num = 2 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateComputerNumWithProjectName(String project_name);

    @Update({
            "update student_it_softwareengineering set num = 2 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateSoftNumWithProjectName(String project_name);



}
