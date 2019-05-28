package edu.develop.leave.controller;

import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.controller.vo.LimitVO;
import edu.develop.leave.model.ClassModel;
import edu.develop.leave.model.LeaveModel;
import edu.develop.leave.service.ClassService;
import edu.develop.leave.service.LeaveService;
import edu.develop.leave.utils.ConditionStr;
import edu.develop.leave.utils.LogUtil;
import edu.develop.leave.utils.SQLUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/9
 * @since jdk8
 */
@RestController
@RequestMapping("class")
@Slf4j
@Api("班级接口")
public class ClassController {

    @Autowired
    ClassService classService;

    /**
     * 获取数据列表
     * 老师查询数据接口
     *
     * @return
     */
    @RequestMapping(value = "findAllClass", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage findAllClass() {
        try {
            List<ClassModel> list = classService.findAllClass();
            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
        }
    }
}
