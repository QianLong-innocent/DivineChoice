package com.dc.service;

import org.springframework.stereotype.Service;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 16:36
 */
public interface StudentBirthService {

    /**
     * 根据学号查询该学生在那个专业
     * @param student_id 学号
     * @return 所属专业
     */
    String selectProfessional(String student_id);

}
