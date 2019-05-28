package edu.develop.leave.controller;

import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.model.TeacherModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @anthor on 2019/5/15
 * @since jdk8
 *
 * 老师接口
 */
@RestController
@RequestMapping("teacher")
@Api(value = "教师接口")
public class TeacherController extends BaseController<TeacherModel> {
}
