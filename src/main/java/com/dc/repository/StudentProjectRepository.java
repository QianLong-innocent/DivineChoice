package com.dc.repository;

import com.dc.domain.StudentProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/20 20:30
 */
@Mapper
public interface StudentProjectRepository {

    @Update({
            "update student_project set name = #{name,jdbcType=VARCHAR},project_id = #{project_id,jdbcType=VARCHAR} where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateStudentProject(StudentProject studentProject);


    @Select({
            "select * from student_project"
    })
    List<StudentProject> selectAllStudentProject();

}
