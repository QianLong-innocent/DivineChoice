package com.dc.repository;

import com.dc.domain.StudentChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/14 16:19
 */
@Mapper
public interface StudentChoiceRepository {

    @Select({
            "select * from student_choice where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    StudentChoice selectStudentChoice(@Param("student_id") String student_id);

    @Update({
            "update student_choice set project_one_name = #{project_one_name,jdbcType=VARCHAR},num = num+1 where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceProjectOne(@Param("student_id") String student_id,@Param("project_one_name") String project_one_name);

    @Update({
            "update student_choice set project_two_name = #{project_two_name,jdbcType=VARCHAR},num = num+1 where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceProjectTwo(@Param("student_id") String student_id,@Param("project_two_name") String project_two_name);

    @Update({
            "update student_choice set project_one_name = '',num = num - 1 where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int deleteChoiceProjectOne(@Param("student_id") String student_id);

    @Update({
            "update student_choice set project_two_name = '',num = num - 1 where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int deleteChoiceProjectTwo(@Param("student_id") String student_id);


    @Select({
            "select * from student_choice"
    })
    List<StudentChoice> selectAllStudentChoice();

    @Update({
            "update student_choice set project_one_name = #{project_one_name,jdbcType=VARCHAR} where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceProjectOneT(@Param("student_id") String student_id,@Param("project_one_name") String project_one_name);

    @Update({
            "update student_choice set project_two_name = #{project_two_name,jdbcType=VARCHAR} where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceProjectTwoT(@Param("student_id") String student_id,@Param("project_two_name") String project_two_name);


    @Update({
            "update student_choice set one_choice = '已选择' where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceOne(String student_id);

    @Update({
            "update student_choice set two_choice = '已选择' where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateChoiceTwo(String student_id);






}
