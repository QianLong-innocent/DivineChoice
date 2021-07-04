package com.dc.service.impl;

import com.dc.domain.ProjectType;
import com.dc.domain.StudentITComputer;
import com.dc.repository.ProjectTypeRespository;
import com.dc.repository.StudentBirthRepository;
import com.dc.service.ProjectTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/11 15:34
 */
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {


    private static final Logger log = LoggerFactory.getLogger(ProjectTypeServiceImpl.class);

    @Resource
    ProjectTypeRespository projectTypeRespository;

    @Resource
    StudentBirthRepository studentBirthRepository;

    @Override
    public List<String> selectProjectType() {
        return projectTypeRespository.selectProjectType();
    }

    @Override
    public List<StudentITComputer> selectDifferentProjectTypeWithSubject(String project_type,String id) {

        // 1. 查询学生所属专业
        String professional = studentBirthRepository.selectProfessional(id);

        // 2. 返回数据
        if("计科".equals(professional)){



            return projectTypeRespository.selectDifferentProjectTypeWithSubjectOne(project_type);
        }else if("软工".equals(professional)){



            return projectTypeRespository.selectDifferentProjectTypeWithSubjectTwo(project_type);
        }else{
            log.info("查不到专业，数据为空！");
            return null;
        }

    }

    @Override
    public List<ProjectType> selectAllProjectType() {
        return projectTypeRespository.selectAllProjectType();
    }

    @Override
    public Boolean insertProjectType(String type) {
        type = type.toUpperCase();
        List<String> list = projectTypeRespository.selectProjectType();
        if(list.contains(type)){
            return false;
        }
        return projectTypeRespository.insertProjectType(type);
    }

    @Override
    public Boolean deleteProjectType(int id) {

        return projectTypeRespository.deleteProjectType(id);
    }
}
