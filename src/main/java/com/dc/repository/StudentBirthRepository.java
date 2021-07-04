package com.dc.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 16:32
 */
@Mapper
public interface StudentBirthRepository {

    // 根据学号查询该学生的专业
    @Select({
            "select professional from student_birth where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    String selectProfessional(String student_id);

}
