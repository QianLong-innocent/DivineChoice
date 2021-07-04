package com.dc.web;

import com.dc.domain.ProjectType;
import com.dc.domain.StudentITComputer;
import com.dc.domain.StudentInfo;
import com.dc.domain.TeacherProject;
import com.dc.service.ProjectTypeService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/11 15:42
 */
@RestController
@RequestMapping("/api")
@Api
public class ProjectTypeController {

    private final ProjectTypeService projectTypeService;

    public ProjectTypeController(ProjectTypeService projectTypeService) {
        this.projectTypeService = projectTypeService;
    }


    @GetMapping("/projectTypes/selectProjectType")
    public ResponseEntity<List<String>> loginByPrimaryKeyWithPassword(){
       return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectTypeService.selectProjectType()));
    }

    @GetMapping("/projectTypes/selectProjectType/{type}")
    public ResponseEntity<List<StudentITComputer>> selectProjectType(@PathVariable String type, String id){
        return ResponseUtil.wrapOrNotFound(Optional.of(projectTypeService.selectDifferentProjectTypeWithSubject(type, id)));
    }

    @GetMapping("/projectTypes/selectAllProjectType")
    public ResponseEntity<List<ProjectType>> selectProjectType(){
        return ResponseUtil.wrapOrNotFound(Optional.of(projectTypeService.selectAllProjectType()));
    }

    @PostMapping("/projectTypes/insertProjectType")
    public ResponseEntity<Boolean> insertProjectType(@RequestBody ProjectType projectType){
        Boolean flag = projectTypeService.insertProjectType(projectType.getProject_type());
        return ResponseUtil.wrapOrNotFound(Optional.of(flag));
    }

    @DeleteMapping("/projectTypes/deleteProjectType/{id}")
    public ResponseEntity<String> deleteProjectType(@PathVariable int id){

        Boolean flag = projectTypeService.deleteProjectType(id);

        return ResponseUtil.wrapOrNotFound(Optional.of("删除成功"));
    }




}
