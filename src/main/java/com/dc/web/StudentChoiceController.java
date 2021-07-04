package com.dc.web;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentChoiceWithTeacherInfo;
import com.dc.service.StudentChoiceService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Select;
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
 * @date 2021/4/14 16:41
 */
@RestController
@RequestMapping("/api")
@Api
public class StudentChoiceController {

    private static final Logger log = LoggerFactory.getLogger(StudentChoiceController.class);
    public final StudentChoiceService studentChoiceService;


    public StudentChoiceController(StudentChoiceService studentChoiceService) {
        this.studentChoiceService = studentChoiceService;
    }

    @GetMapping("/studentChoices/selectStudentChoice")
    public ResponseEntity<StudentChoice> selectStudentChoice(String student_id){
        StudentChoice studentChoice = studentChoiceService.selectStudentChoice(student_id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(studentChoice));
    }

    @PutMapping("/studentChoices/updateChoiceProject")
    public ResponseEntity<String> updateChoiceProject(@RequestBody Map<String,String> params){
        // 3个参数。学号、选题名字、第几志愿
        String student_id = params.get("student_id");
        String name = params.get("name");
        String volunteer = params.get("volunteer");
        int flag = studentChoiceService.updateChoiceProject(student_id,name,volunteer);
        if(flag == 0){
            log.info("请勿重复选择，如要重选先取消志愿");
            return new ResponseEntity<>("志愿已经使用过！", HttpStatus.BAD_REQUEST);
        }else if(flag == 1){
            log.info("选题成功！");
            return new ResponseEntity<>("选题成功！", HttpStatus.OK);
        }else if(flag == 2){
            log.info("志愿已选满");
            return new ResponseEntity<>("志愿已选满", HttpStatus.BAD_REQUEST);
        }else if(flag == 3){
            log.info("两个志愿不可一样");
            return new ResponseEntity<>("两个志愿不可一样", HttpStatus.BAD_REQUEST);
        }else{
            log.info("已经建立关系");
            return new ResponseEntity<>("手下留情，已经有一个志愿被选择", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/studentChoices/selectChoiceStateAndTeacherInfo")
    public ResponseEntity<List<StudentChoiceWithTeacherInfo>> selectChoiceStateAndTeacherInfo(String id){
        log.info("该学生学号：{} ",id);
        List<StudentChoiceWithTeacherInfo> list = studentChoiceService.selectChoiceStateAndTeacherInfo(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/studentChoices/deleteChoiceProject")
    public ResponseEntity<String> deleteChoiceProject(@RequestBody Map<String,String> params){
        log.info("学生：{} 正在执行退选操作! {} ",params.get("id"),params.get("volunteer"));
        int flag = studentChoiceService.deleteChoiceProject(params.get("id"),params.get("volunteer"));
        if(flag == 10){
            return new ResponseEntity<>("已建立连接关系，无法退选",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
