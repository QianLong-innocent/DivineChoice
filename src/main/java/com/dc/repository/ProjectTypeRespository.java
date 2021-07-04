package com.dc.repository;

import com.dc.domain.ProjectType;
import com.dc.domain.StudentITComputer;
import com.dc.domain.TeacherProject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/11 15:10
 */
@Mapper
public interface ProjectTypeRespository {

    // 查询出所有的题目类型
    @Select({
            "SELECT project_type from project_type"
    })
    List<String> selectProjectType();


    // 计科
    // 根据题目类型 + 学生喜爱的题目类型
    // id 项目编号 项目名称 项目内容
    @Select({
            "SELECT * FROM student_it_computer WHERE project_type = #{project_type,jdbcType=VARCHAR} and num < 2"
    })
    List<StudentITComputer> selectDifferentProjectTypeWithSubjectOne(@Param("project_type") String project_type);

    // 软工
    @Select({
            "SELECT * FROM student_it_softwareengineering WHERE project_type = #{project_type,jdbcType=VARCHAR} and num < 2"
    })
    List<StudentITComputer> selectDifferentProjectTypeWithSubjectTwo(@Param("project_type") String project_type);


    @Update({
            "update student_it_computer set num = num + 1 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateStudentITComputer(String project_name);

    @Update({
            "update student_it_softwareengineering set num = num + 1 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateStudentITSoftwareengineering(String project_name);

    @Update({
            "update student_it_computer set num = num - 1 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int deleteStudentITComputer(String project_name);

    @Update({
            "update student_it_softwareengineering set num = num - 1 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int deleteStudentITSoftwareengineering(String project_name);


    @Select({
            "select * from project_type"
    })
    List<ProjectType> selectAllProjectType();

    @Insert({
        "insert into project_type (project_type) VALUES  (#{project_type,jdbcType=VARCHAR});"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    Boolean insertProjectType(String project_type);


    @Delete({
            "delete from project_type where id = #{id,jdbcType=INTEGER}"
    })
    Boolean deleteProjectType(int id);
}
