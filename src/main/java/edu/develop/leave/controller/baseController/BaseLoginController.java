package edu.develop.leave.controller.baseController;

import edu.develop.leave.model.Token;
import edu.develop.leave.service.baseService.BaseLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @anthor on 2019/5/7
 * @since jdk8
 *
 * 用户登录接口
 */
@Api(value = "登录接口")
@RestController
@RequestMapping("44551")
public class BaseLoginController {

    @Autowired
    BaseLoginService baseLoginService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Map<String,Object> login(@RequestParam("no") Long no,
                                    @RequestParam("password") String password,
                                    @RequestParam("roleType") Integer roleType,
                                    HttpSession session){
          Map<String,Object> resultMap = new HashMap<>();
          Token token = baseLoginService.login(no,password,roleType);
          if(token == null){
           resultMap.put("code",-1);
           resultMap.put("msg","账号或密码错误");
           resultMap.put("token",null);
           return resultMap;
          }
          resultMap.put("code",1);
          resultMap.put("msg","登录成功");
          resultMap.put("token",token);
          session.setAttribute("token",token);
          return resultMap;

    }

    @RequestMapping(value = "/resetpassw",method = {RequestMethod.PUT})
    public Map<String,Object> resetPassword(@RequestParam("password") String password,
                                            @RequestParam("id") Long id,
                                            @RequestParam("role") Integer role){

           Map<String,Object> resultMap = new HashMap<>();

           Integer status = baseLoginService.resetPassword(password,id,role);
           if(status == 0 || status == null){
               resultMap.put("code",-1);
           }
           resultMap.put("code",1);

           return  resultMap;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginout",method = {RequestMethod.GET})
    public Map<String,Object> loginout(HttpSession session){
        Map<String,Object> resultMap = new HashMap<>();
       session.setAttribute("token",null);
       resultMap.put("status",1);
       return resultMap;
    }
}
