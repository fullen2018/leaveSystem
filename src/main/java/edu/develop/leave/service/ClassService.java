package edu.develop.leave.service;

import edu.develop.leave.dao.mapper.ClassMapper;
import edu.develop.leave.model.ClassModel;
import edu.develop.leave.service.baseService.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/9
 * @since jdk8
 *
 * 班级管理业务层
 */
@Service
public class ClassService extends BaseService<ClassModel> {

    @Resource
    ClassMapper classMapper;

    public List<ClassModel> findAllClass(){
       return  classMapper.findAllClass();
    }
}
