package com.dc.repository;

import com.dc.domain.StudentITComputer;
import org.apache.ibatis.annotations.*;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/19 21:36
 */
@Mapper
public interface StudentITComputerRepository {

    @Insert({
        "insert into student_it_computer (project_name,project_type,project_content,num) values(#{project_name,jdbcType=VARCHAR}," +
                "#{project_type,jdbcType=VARCHAR},#{project_content,jdbcType=VARCHAR},0)"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertProjectForComputer(StudentITComputer studentITComputer);

    @Insert({
            "insert into student_it_softwareengineering (project_name,project_type,project_content,num) values(#{project_name,jdbcType=VARCHAR}," +
                    "#{project_type,jdbcType=VARCHAR},#{project_content,jdbcType=VARCHAR},0)"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertProjectForSoft(StudentITComputer studentITComputer);

    @Update({
            "update student_it_computer " +
                    "set project_name = #{project_newName,jdbcType=VARCHAR},project_content = #{project_content,jdbcType=VARCHAR} " +
                    "where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateProjectForComputer(@Param("project_name") String project_name, @Param("project_newName") String project_newName, @Param("project_content") String project_content);

    @Update({
            "update student_it_softwareengineering " +
                    "set project_name = #{project_newName,jdbcType=VARCHAR},project_content = #{project_content,jdbcType=VARCHAR} " +
                    "where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateProjectForSoft(@Param("project_name") String project_name, @Param("project_newName") String project_newName, @Param("project_content") String project_content);

    @Delete({
            "delete from student_it_computer where id = #{id,jdbcType=VARCHAR} and project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int deleteProjectForComputer(@Param("id") String id,@Param("project_name") String project_name);

    @Delete({
            "delete from student_it_softwareengineering where id = #{id,jdbcType=VARCHAR} and project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int deleteProjectForSoft(@Param("id") String id,@Param("project_name") String project_name);





}
