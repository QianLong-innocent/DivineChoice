package com.dc.web;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import com.dc.service.StudentInfoService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/3/29 23:01
 */
@RestController
@RequestMapping("/api")
@Api
public class StudentInfoController {

    private static final Logger log = LoggerFactory.getLogger(StudentInfoController.class);

    // 如此这般就让控制层拥有了业务层
    private final StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    /**
     * 登陆检测
     * @param params post两个请求。username + password  学号 + 密码
     * @return
     */
    @PostMapping("/studentInfos/loginByPrimaryKeyWithPassword")
    public ResponseEntity<StudentInfo> loginByPrimaryKeyWithPassword(@RequestBody Map<String,Object> params){

        StudentInfo studentInfo = studentInfoService.loginByPrimaryKeyWithPassword((String)params.get("username"),(String)params.get("password"));
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(studentInfo));

    }

    /**
     * 修改密码
     * @param params 学号 + 新密码 + 原密码
     * @return
     */
    @PutMapping("/studentInfos/updateByPrimaryKeyChangePassword")
    public ResponseEntity<String> updateByPrimaryKeyChangePassword(@RequestBody Map<String,String> params){
        String id = params.get("student_id");
        String password = params.get("password");
        String passwordBefore = params.get("passwordBefore");

        int flag = studentInfoService.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
        if(flag == 0) {
            log.info("原密码错误");
            return new ResponseEntity<>("原密码错误", HttpStatus.NOT_FOUND);
        } else{
            log.info("密码更新成功 新密码 ：{} 旧密码 ：{}",password,passwordBefore);
            return new ResponseEntity<>("更新密码成功！",HttpStatus.OK);
        }
    }

    /**
     * 修改个人信息
     * @param studentInfo
     * @return
     */
    @PutMapping("/studentInfos/updateByPrimaryKeyChangeMessage")
    public ResponseEntity<StudentInfo> updateByPrimaryKeyChangeMessage(@RequestBody StudentInfo studentInfo){
        int flag = studentInfoService.updateByPrimaryKeyChangeMessage(studentInfo);
        if(flag == 0){
            log.info("未找到该用户");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            log.info("用户个人信息更新成功 {} ",studentInfo);
            return new ResponseEntity<>(studentInfo,HttpStatus.OK);
        }
    }

    @GetMapping("/studentInfos/selectAllStudent")
    public ResponseEntity<List<StudentInfoFinal>> selectAllStudent(){
        List<StudentInfoFinal> list = studentInfoService.selectAllStudent();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

    @GetMapping("/studentInfos/selectStudentByName")
    public ResponseEntity<List<StudentInfoFinal>> selectStudentByName(String studentName){
        List<StudentInfoFinal> list = studentInfoService.selectStudentByName('%'+studentName+'%');
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

    @PostMapping("/studentInfos/updateStudentInfo")
    public ResponseEntity<String> updateStudentInfo(@RequestBody StudentInfo studentInfo){

        studentInfoService.updateStudentInfo(studentInfo);

        return new ResponseEntity<>("修改成功",HttpStatus.OK);
    }

    @PostMapping("/studentInfos/insertStudentInfo")
    public ResponseEntity<String> insertStudentInfo(@RequestBody StudentInfoFinal studentInfoFinal){
        Boolean flag = studentInfoService.insertStudent(studentInfoFinal);
        if(flag){
            return new ResponseEntity<>("学生注册成功,学号: " + studentInfoFinal.getStudent_id(),HttpStatus.OK);
        }
        return new ResponseEntity<>("该学号已使用过，请重新注册",HttpStatus.BAD_REQUEST);
    }
}