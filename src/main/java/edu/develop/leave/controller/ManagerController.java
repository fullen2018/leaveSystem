package edu.develop.leave.controller;

import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.model.ManagerModel;
import edu.develop.leave.model.Token;
import edu.develop.leave.service.ManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @anthor on 2019/5/15
 * @since jdk8
 *
 * 管理员接口
 */
@RestController
@RequestMapping("manager")
@Api("管理员接口")
public class ManagerController extends BaseController<ManagerModel> {

    @Autowired
    ManagerService managerService;


    /**
     * 管理员登录
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public RespondsMessage login(@RequestParam("id") Integer id, @RequestParam("password") String password, HttpSession session) {
        Integer status = managerService.login(id,password);
        if(status == 0){
            return RespondsMessage.failure(null,status);
        }else {
            session.setAttribute("token",new Token());
            return RespondsMessage.success(null,status);
        }
    }
}
