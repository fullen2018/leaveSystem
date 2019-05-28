package edu.develop.leave.controller;

import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.model.LeaderModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @anthor on 2019/5/15
 * @since jdk8
 *
 * 领导接口
 */
@RestController
@RequestMapping("leader")
@Api(value = "领导接口")
public class LeaderController extends BaseController<LeaderModel> {
}
