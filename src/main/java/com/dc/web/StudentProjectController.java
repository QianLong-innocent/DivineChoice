package com.dc.web;

import com.dc.domain.TeacherInfo;
import com.dc.service.StudentProjectService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/25 15:22
 */
@RestController
@RequestMapping("/api")
@Api
public class StudentProjectController {
    private static final Logger log = LoggerFactory.getLogger(StudentProjectController.class);

    private final StudentProjectService studentProjectService;

    public StudentProjectController(StudentProjectService studentProjectService) {
        this.studentProjectService = studentProjectService;
    }

    @GetMapping("/studentProjects/selectAllStudentProject")
    public ResponseEntity<List<Integer>> selectAllStudentProject(){
        List<Integer> list = studentProjectService.selectAllStudentProject();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
