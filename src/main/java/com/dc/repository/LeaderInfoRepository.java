package com.dc.repository;

import com.dc.domain.LeaderInfo;
import com.dc.domain.StudentChoice;
import com.dc.domain.StudentProject;
import com.dc.domain.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 15:19
 */
@Mapper
public interface LeaderInfoRepository {


    // 登陆.查找数据
    @Select({
            "select leader_id,password,name,id_card,sex," +
                    "birthday,phone,email,remark " +
                    "from leader_info " +
                    "where leader_id = #{leader_id,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    LeaderInfo selectByPrimaryKeyWithPassword(@Param("leader_id") String leader_id, @Param("password") String password);


    // 修改密码
    @Update({
            "update leader_info " +
                    "set password = #{password,jdbcType=VARCHAR} " +
                    "where leader_id = #{leader_id,jdbcType=VARCHAR} and password = #{passwordBefore,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangePassword(@Param("leader_id") String leader_id,@Param("password") String password,@Param("passwordBefore") String passwordBefore);

    // 修改个人信息
    @Update({
            "update leader_info",
            "set id_card = #{id_card,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "birthday = #{birthday,jdbcType=TIMESTAMP},",
            "phone = #{phone,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR}",
            "where leader_id = #{leader_id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyChangeMessage(LeaderInfo leaderInfo);




    // 主任退选题目之后，所有都退选
    @Update({
            "update student_choice SET num = 0," +
                    "project_one_name = #{project_one_name,jdbcType=VARCHAR}," +
                    "one_choice = #{one_choice,jdbcType=VARCHAR}," +
                    "project_two_name = #{project_two_name,jdbcType=VARCHAR}," +
                    "two_choice = #{two_choice,jdbcType=VARCHAR} " +
                    "WHERE student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateStudentChoice(StudentChoice studentChoice);

    @Update({
            "update student_project set name = '',project_id = '' where student_id = #{student_id,jdbcType=VARCHAR}"
    })
    int updateStudentProject(@Param("student_id") String student_id);

    @Update({
            "update teacher_project set num = 0 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateTeacherProject(@Param("project_name") String project_name);

    @Update({
            "update student_it_computer set num = 0 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateStudentITComputer(@Param("project_name") String project_name);

    @Update({
            "update student_it_softwareengineering set num = 0 where project_name = #{project_name,jdbcType=VARCHAR}"
    })
    int updateStudentITSoftwareengineering(@Param("project_name") String project_name);

}
