package com.dc.service.impl;

import com.dc.repository.StudentBirthRepository;
import com.dc.service.StudentBirthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/8 16:38
 */
@Service
public class StudentBirthServiceImpl implements StudentBirthService {

    @Resource
    StudentBirthRepository studentBirthRepository;

    @Override
    public String selectProfessional(String student_id) {
        return studentBirthRepository.selectProfessional(student_id);
    }
}
