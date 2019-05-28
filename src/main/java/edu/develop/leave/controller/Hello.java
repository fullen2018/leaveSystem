package edu.develop.leave.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @anthor on 2019/5/8
 * @since jdk8
 */
@RestController
@Api("测试接口")
public class Hello {


    @RequestMapping("/hello")
    public String sqy(){
        return "hello";
    }
}
