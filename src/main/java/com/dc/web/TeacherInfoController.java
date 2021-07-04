package com.dc.web;

import com.dc.domain.StudentInfo;
import com.dc.domain.StudentInfoFinal;
import com.dc.domain.TeacherInfo;
import com.dc.service.TeacherInfoService;
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
 * @date 2021/4/4 9:32
 */
@RestController
@RequestMapping("/api")
@Api
public class TeacherInfoController {

    private static final Logger log = LoggerFactory.getLogger(TeacherInfoController.class);

    private final TeacherInfoService teacherInfoService;


    public TeacherInfoController(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    /**
     * 登陆检测
     * @param params post两个请求。username + password  工号 + 密码
     * @return
     */
    @PostMapping("/teacherInfos/loginByPrimaryKeyWithPassword")
    public ResponseEntity<TeacherInfo> loginByPrimaryKeyWithPassword(@RequestBody Map<String,Object> params){
        TeacherInfo teacherInfo = teacherInfoService.loginByPrimaryKeyWithPassword((String)params.get("username"),(String)params.get("password"));

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(teacherInfo));
    }

    /**
     * 修改密码
     * @param params 学号 + 新密码 + 原密码
     * @return
     */
    @PutMapping("/teacherInfos/updateByPrimaryKeyChangePassword")
    public ResponseEntity<String> updateByPrimaryKeyChangePassword(@RequestBody Map<String,String> params){
        String id = params.get("teacher_id");
        String password = params.get("password");
        String passwordBefore = params.get("passwordBefore");

        int flag = teacherInfoService.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
        if(flag == 0) {
            log.info("原密码错误");
            return new ResponseEntity<>("原密码错误", HttpStatus.NOT_FOUND);
        } else {
            log.info("密码更新成功 新密码 ：{} 旧密码 ：{}",password,passwordBefore);
            return new ResponseEntity<>("更新密码成功！",HttpStatus.OK);
        }
    }

    /**
     * 修改个人信息
     * @param teacherInfo
     * @return
     */
    @PutMapping("/teacherInfos/updateByPrimaryKeyChangeMessage")
    public ResponseEntity<TeacherInfo> updateByPrimaryKeyChangeMessage(@RequestBody TeacherInfo teacherInfo){
        int flag = teacherInfoService.updateByPrimaryKeyChangeMessage(teacherInfo);
        if(flag == 0){
            log.info("未找到该用户");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            log.info("用户个人信息更新成功 {} ",teacherInfo);
            return new ResponseEntity<>(teacherInfo,HttpStatus.OK);
        }
    }


    @GetMapping("/teacherInfos/selectAllTeacher")
    public ResponseEntity<List<TeacherInfo>> selectAllTeacher(){
        List<TeacherInfo> list = teacherInfoService.selectAllTeacher();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

    @GetMapping("/teacherInfos/selectTeacherByName")
    public ResponseEntity<List<TeacherInfo>> selectTeacherByName(String teacherName){
        List<TeacherInfo> list = teacherInfoService.selectTeacherByName('%'+teacherName+'%');
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(list));
    }

    @PostMapping("/teacherInfos/updateTeacherInfo")
    public ResponseEntity<String> updateTeacherInfo(@RequestBody TeacherInfo teacherInfo){

        teacherInfoService.updateTeacherInfo(teacherInfo);

        return new ResponseEntity<>("修改成功",HttpStatus.OK);
    }


    @PostMapping("/teacherInfos/insertTeacherInfo")
    public ResponseEntity<String> insertTeacherInfo(@RequestBody TeacherInfo teacherInfo){
        Boolean flag = teacherInfoService.insertTeacherInfo(teacherInfo);
        if(flag)
            return new ResponseEntity<>("教师注册成功.工号:" + teacherInfo.getTeacher_id(),HttpStatus.OK);
        return new ResponseEntity<>("该工号已使用过，请重新注册",HttpStatus.BAD_REQUEST);
    }

}