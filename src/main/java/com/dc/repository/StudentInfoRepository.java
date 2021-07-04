package com.dc.repository;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 15:26
 */
@Mapper
public interface StudentInfoRepository {

    // 登陆.查找数据
    @Select({
            "select student_id , password , name , id_card , sex, " +
                    "birthday , phone , email , remark " +
                    "from student_info " +
                    "where student_id = #{student_id,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    StudentInfo selectByPrimaryKeyWithPassword(@Param("student_id") String student_id, @Param("password") String password);


    // 修改密码
    @Update({
            "update student_info " +
                    "set password = #{password,jdbcType=VARCHAR} " +
                    "where student_id = #{student_id,jdbcType=VARCHAR} and password = #{passwordBefore,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangePassword(@Param("student_id") String student_id,@Param("password") String password,@Param("passwordBefore") String passwordBefore);


    // 修改个人信息
    @Update({
            "update student_info",
            "set id_card = #{id_card,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "birthday = #{birthday,jdbcType=TIMESTAMP},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR}",
            "where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangeMessage(StudentInfo studentInfo);


    @Select({
            "select * from student_info where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    StudentInfo selectStudent(String student_id);


    @Select({
            "select a.student_id,a.`password`,a.`name`,a.id_card,a.sex,a.birthday,a.phone,a.email,a.remark,b.department,b.professional \n" +
                    "from student_info a \n" +
                    "left join student_birth b \n" +
                    "on a.student_id = b.student_id"
    })
    List<StudentInfoFinal> selectAllStudent();


    @Select({
            "select a.student_id,a.`password`,a.`name`,a.id_card,a.sex,a.birthday,a.phone,a.email,a.remark,b.department,b.professional \n" +
                    "from student_info a \n" +
                    "left join student_birth b \n" +
                    "on a.student_id = b.student_id \n" +
                    "where name like #{name,jdbcType=VARCHAR}"
    })
    List<StudentInfoFinal> selectStudentByName(String name);


    @Update({
            "update student_info set password = #{password,jdbcType=VARCHAR},name = #{name,jdbcType=VARCHAR} " +
                    "where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateStudentInfo(StudentInfo studentInfo);

    @Insert({
            "insert into student_info (student_id,`password`,`name`) VALUES (#{student_id,jdbcType=VARCHAR}," +
                    "'123',#{name,jdbcType=VARCHAR})"
    })
    int insertStudentInfo(StudentInfoFinal studentInfoFinal);


    @Insert({
            "insert into student_birth (student_id,department,professional) values (#{student_id,jdbcType=VARCHAR},'电气信息学院',#{professional,jdbcType=VARCHAR})"
    })
    int insertStudentBirth(StudentInfoFinal studentInfoFinal);

    @Insert({
            "insert into student_choice (student_id,num) values (#{student_id,jdbcType=VARCHAR},'0')"
    })
    int insertStudentChoice(StudentInfoFinal studentInfoFinal);

    @Insert({
            "insert into student_project (student_id) values (#{student_id,jdbcType=VARCHAR})"
    })
    int insertStudentProject(StudentInfoFinal studentInfoFinal);

}
