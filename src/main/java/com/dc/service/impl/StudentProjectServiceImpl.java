package com.dc.service.impl;

import com.dc.domain.StudentChoice;
import com.dc.domain.StudentProject;
import com.dc.repository.StudentChoiceRepository;
import com.dc.repository.StudentProjectRepository;
import com.dc.service.StudentChoiceService;
import com.dc.service.StudentProjectService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/25 15:26
 */
@Service
public class StudentProjectServiceImpl implements StudentProjectService {

    @Resource
    StudentProjectRepository studentProjectRepository;

    @Resource
    StudentChoiceRepository studentChoiceRepository;
    @Override
    public List<Integer> selectAllStudentProject() {
        List<StudentProject> list = studentProjectRepository.selectAllStudentProject();
        int notReach = 0;
        int reach = 0;
        int notSelect = 0;
        for(StudentProject i : list){
            if(i.getName() == null || "".equals(i.getName())){
                ;
            }else{
                reach++;
            }
        }
        List<StudentChoice> studentChoice = studentChoiceRepository.selectAllStudentChoice();
        for(StudentChoice i : studentChoice){
            if(i.getNum() == 0){
                notSelect++;
            }
        }
        notReach = studentChoice.size() - notSelect - reach;
//        System.out.println(notReach + " " + reach + " " + notSelect);
        List<Integer> integerList = new ArrayList<>();
        integerList.add(reach);
        integerList.add(notReach);
        integerList.add(notSelect);
        return integerList;
    }
}
