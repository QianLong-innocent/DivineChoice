package com.dc.web;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentChoiceWithTeacherInfo;
import com.dc.domain.TeacherChoice;
import com.dc.domain.TeacherProject;
import com.dc.service.TeacherProjectService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 15:45
 */
@RestController
@RequestMapping("/api")
@Api
public class TeacherProjectController {


    private final TeacherProjectService teacherProjectService;


    public TeacherProjectController(TeacherProjectService teacherProjectService) {
        this.teacherProjectService = teacherProjectService;
    }


    @GetMapping("/teacherProjects/selectTeacherName")
    public ResponseEntity<List<String>> selectTeacherName(){
        List<String> list = teacherProjectService.selectTeacherName();
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }


    @GetMapping("/teacherProjects/selectTeacherProject/{name}")
    public ResponseEntity<List<TeacherProject>> selectTeacherProject(@PathVariable String name,String id){
        List<TeacherProject> list = teacherProjectService.selectTeacherProject(name,id);
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }

    @GetMapping("/teacherProjects/selectAllTeacherProject")
    public ResponseEntity<List<TeacherProject>> selectAllTeacherProject(){
        List<TeacherProject> list = teacherProjectService.selectAllTeacherProject();
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }

    @GetMapping("/teacherProjects/selectAllTeacherProjectForComputer")
    public ResponseEntity<List<TeacherProject>> selectAllTeacherProjectForComputer(String teacherName){
        List<TeacherProject> list = teacherProjectService.selectAllTeacherProjectForComputer(teacherName);
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }

    @GetMapping("/teacherProjects/selectAllTeacherProjectForSoft")
    public ResponseEntity<List<TeacherProject>> selectAllTeacherProjectForSoft(String teacherName){
        List<TeacherProject> list = teacherProjectService.selectAllTeacherProjectForSoft(teacherName);
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }

    // 老师开始选学生，数据
    @GetMapping("/teacherProjects/selectAllTeacherProjectForComputerForChoice")
    public ResponseEntity<List<TeacherChoice>> selectAllTeacherProjectForComputerForChoice(String teacherName){
        List<TeacherChoice> list = teacherProjectService.selectAllTeacherProjectForComputerForChoice(teacherName);
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }

    @GetMapping("/teacherProjects/selectAllTeacherProjectForSoftForChoice")
    public ResponseEntity<List<TeacherChoice>> selectAllTeacherProjectForSoftForChoice(String teacherName){
        List<TeacherChoice> list = teacherProjectService.selectAllTeacherProjectForSoftForChoice(teacherName);
        return ResponseUtil.wrapOrNotFound(Optional.of(list));
    }


    @PutMapping("/teacherProjects/updateProjectNameAndContent")
    public ResponseEntity<String> updateProjectNameAndContent(@RequestBody Map<String,String> params){
        String name = params.get("project_name");
        String newName = params.get("project_newName");
        String content = params.get("project_content");
        String professional = params.get("professional");

        // 更新之前需要判断：项目名是否重复

        int flag = teacherProjectService.updateProjectNameAndContent(name,newName,content,professional);
//        System.out.println(flag);
        if(flag == 2)
            return new ResponseEntity<>("项目名重复", HttpStatus.BAD_REQUEST);
        else if(flag == 1)
            return new ResponseEntity<>("更新成功", HttpStatus.OK);
        else
            return new ResponseEntity<>("更新失败", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/teacherProjects/insertProject/{project_type}")
    public ResponseEntity<String> insertProject(@RequestBody TeacherProject teacherProject,@PathVariable String project_type){

        int flag = teacherProjectService.insertProject(teacherProject,project_type);
        if(flag == 2){
            return new ResponseEntity<>("项目名重复", HttpStatus.BAD_REQUEST);
        } else if(flag == 1)
            return new ResponseEntity<>("插入成功", HttpStatus.OK);
        else
            return new ResponseEntity<>("插入失败", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/teacherProjects/deleteProject")
    public ResponseEntity<String> deleteProject(@RequestBody Map<String ,String> params){

        int flag = teacherProjectService.deleteProject(params.get("project_id"),params.get("professional"),params.get("project_name"));

//        System.out.println(flag);
        if(flag == -1){
            return new ResponseEntity<>("已建立连接关系不可删除",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("删除成功",HttpStatus.OK);

    }


    @PostMapping("/teacherProjects/choiceStudent")
    public ResponseEntity<String> choiceStudent(@RequestBody TeacherChoice teacherChoice){


        int flag = teacherProjectService.choiceStudent(teacherChoice);
        if(flag == 2){
            return new ResponseEntity<>("你已选择，切勿重新选择",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("选择成功",HttpStatus.OK);
    }


}
