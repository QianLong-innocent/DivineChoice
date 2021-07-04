package com.dc.web;

import com.dc.service.StudentBirthService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 16:40
 */
@RestController
@RequestMapping("/api")
@Api
public class StudentBirthController {

    private final StudentBirthService studentBirthService;

    public StudentBirthController(StudentBirthService studentBirthService) {
        this.studentBirthService = studentBirthService;
    }


    @GetMapping("/studentBirth/selectProfessional")
    public ResponseEntity<String> selectProfessional(String student_id){
        String professional = studentBirthService.selectProfessional(student_id);
        return ResponseUtil.wrapOrNotFound(Optional.of(professional));
    }

}
