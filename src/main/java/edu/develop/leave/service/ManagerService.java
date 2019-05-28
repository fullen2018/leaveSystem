package edu.develop.leave.service;

import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.dao.mapper.ManagerMapper;
import edu.develop.leave.model.ManagerModel;
import edu.develop.leave.service.baseService.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @anthor on 2019/5/15
 * @since jdk8
 *
 * 管理员服务层
 */
@Service
public class ManagerService  extends BaseService<ManagerModel> {

    @Resource
    ManagerMapper managerMapper;

    /**
     * 管理员登录
     * @param id
     * @param password
     * @return
     */
    public Integer login(Integer id,String password){
          return managerMapper.login(id,password);
    }
}
