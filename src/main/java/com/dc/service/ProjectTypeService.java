package com.dc.service;

import com.dc.domain.ProjectType;
import com.dc.domain.StudentITComputer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxi
 * @version 1.0
 * @date 2021/4/11 15:33
 */
public interface ProjectTypeService {


    /**
     * 查询出所有题目类型
     * @return
     */
    List<String> selectProjectType();

    /**
     * 根据学生相应专业，查询对应的题目类型所有题型
     * @param type 项目类型
     * @param id 学号
     * @return 毕设项目
     */
    List<StudentITComputer> selectDifferentProjectTypeWithSubject(String type,String id);

    /**
     * 查询出所有题目 + id
     * @return
     */
    List<ProjectType> selectAllProjectType();

    /**
     * 插入一条数据
     * @return
     */
    Boolean insertProjectType(String type);


    /**
     * 删除一条数据
     * @param id
     * @return
     */
    Boolean deleteProjectType(int id);

}
