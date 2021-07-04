package com.dc.repository;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import com.dc.domain.TeacherInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 15:28
 */
@Mapper
public interface TeacherInfoRepository {

    // 登陆.查找数据
    @Select({
            "select teacher_id,password,name,id_card,sex," +
                    "birthday,phone,email,remark " +
                    "from teacher_info " +
                    "where teacher_id = #{teacher_id,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    TeacherInfo selectByPrimaryKeyWithPassword(@Param("teacher_id") String teacher_id, @Param("password") String password);


    // 根据老师名：查询电话
    @Select({
            "select phone from teacher_info where name = #{name,jdbcType=VARCHAR}"
    })
    List<String> selectPhoneByTeacherName(@Param("name") String name);


    // 修改密码
    @Update({
            "update teacher_info " +
                    "set password = #{password,jdbcType=VARCHAR} " +
                    "where teacher_id = #{teacher_id,jdbcType=VARCHAR} and password = #{passwordBefore,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangePassword(@Param("teacher_id") String teacher_id,@Param("password") String password,@Param("passwordBefore") String passwordBefore);

    // 修改个人信息
    @Update({
            "update teacher_info",
            "set id_card = #{id_card,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "birthday = #{birthday,jdbcType=TIMESTAMP},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR}",
            "where teacher_id = #{teacher_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangeMessage(TeacherInfo teacherInfo);

    @Select({
            "select * from teacher_info"
    })
    List<TeacherInfo> selectAllTeacher();

    @Select({
            "select * from teacher_info where name like #{name,jdbcType=VARCHAR}"
    })
    List<TeacherInfo> selectTeacherByName(String name);

    @Update({
            "update teacher_info set password = #{password,jdbcType=VARCHAR},name = #{name,jdbcType=VARCHAR} " +
                    "where teacher_id = #{teacher_id,jdbcType=VARCHAR}"
    })
    int updateTeacherInfo(TeacherInfo teacherInfo);

    @Select({
            "select * from teacher_info where teacher_id = #{teacher_id,jdbcType=VARCHAR}"
    })
    TeacherInfo selectTeacherInfo(TeacherInfo teacherInfo);


    @Insert({
            "insert into teacher_info (teacher_id,`password`,`name`) VALUES (#{teacher_id,jdbcType=VARCHAR}," +
                    "'123',#{name,jdbcType=VARCHAR})"
    })
    int insertTeacherInfo(TeacherInfo teacherInfo);


}
