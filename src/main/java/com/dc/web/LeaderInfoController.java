package com.dc.web;

import com.dc.domain.LeaderInfo;
import com.dc.domain.TeacherInfo;
import com.dc.service.LeaderInfoService;
import com.dc.utils.ResponseUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/4 9:38
 */
@RestController
@RequestMapping("/api")
@Api(value = "/api")
public class LeaderInfoController {

    private static final Logger log = LoggerFactory.getLogger(LeaderInfoController.class);


    private final LeaderInfoService leaderInfoService;

    public LeaderInfoController(LeaderInfoService leaderInfoService) {
        this.leaderInfoService = leaderInfoService;
    }

    /**
     * 登陆检测
     * @param params post两个请求。username + password  工号 + 密码
     * @return
     */
    @PostMapping("/leaderInfos/loginByPrimaryKeyWithPassword")
    public ResponseEntity<LeaderInfo> loginByPrimaryKeyWithPassword(@RequestBody Map<String,Object> params){

        LeaderInfo leaderInfo = leaderInfoService.loginByPrimaryKeyWithPassword((String)params.get("username"),(String)params.get("password"));

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(leaderInfo));
    }

    /**
     * 修改密码
     * @param params 学号 + 新密码 + 原密码
     * @return
     */
    @PutMapping("/leaderInfos/updateByPrimaryKeyChangePassword")
    public ResponseEntity<String> updateByPrimaryKeyChangePassword(@RequestBody Map<String,String> params){
        String id = params.get("leader_id");
        String password = params.get("password");
        String passwordBefore = params.get("passwordBefore");

        int flag = leaderInfoService.updateByPrimaryKeyChangePassword(id,password,passwordBefore);
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
     * @param leaderInfo
     * @return
     */
    @PutMapping("/leaderInfos/updateByPrimaryKeyChangeMessage")
    public ResponseEntity<LeaderInfo> updateByPrimaryKeyChangeMessage(@RequestBody LeaderInfo leaderInfo){
        int flag = leaderInfoService.updateByPrimaryKeyChangeMessage(leaderInfo);
        if(flag == 0){
            log.info("未找到该用户");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            log.info("用户个人信息更新成功 {} ",leaderInfo);
            return new ResponseEntity<>(leaderInfo,HttpStatus.OK);
        }
    }


    @PostMapping("/leaderInfos/insertUser")
    public ResponseEntity<String> insertUser(@RequestBody Map<String,String> params){


        return new ResponseEntity<>("注册成功！",HttpStatus.OK);
    }


    /**
     * 主任执行退选操作
     * @param params
     * @return
     */
    @DeleteMapping("/leaderInfos/deleteUserChoice")
    public ResponseEntity<String> deleteUserChoice(@RequestBody Map<String,String> params){

        log.info("主任开始对 {} 执行最厉害的退题权利",params.get("student_id"));



        String content = leaderInfoService.deleteUserChoice(params);

        System.out.println(content);
        if(!content.equals("退题成功！"))
            return new ResponseEntity<>(content,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(content,HttpStatus.OK);
    }




}
