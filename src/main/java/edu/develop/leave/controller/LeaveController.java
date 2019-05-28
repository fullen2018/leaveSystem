package edu.develop.leave.controller;

import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.controller.vo.LimitVO;
import edu.develop.leave.model.LeaveModel;
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
 * @anthor on 2019/5/8
 * @since jdk8
 */
@RestController
@RequestMapping("leave")
@Api(value = "请假条接口",description = "请假条基本接口")
@Slf4j
public class LeaveController extends BaseController<LeaveModel> {

    @Autowired
    LeaveService leaveService;

    /**
     * 获取数据列表
     * 老师查询数据接口
     *
     * @return
     */
    @RequestMapping(value = "teacherSlist", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage teacherSlist(@ModelAttribute LeaveModel m, @ModelAttribute LimitVO limitVO,Integer teacherId) {
        Class clazz = m.getClass();
        // 获取被调用对象的名称
        getInvokeObjName();
        Object[] values = getParameters(clazz, m);
        String[] names = getParameterNames(clazz, m);

        List<LeaveModel> list = null;
        Long total;
        String sql = SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values);
        try {
            // 条件查询
            list =  leaveService.teacherSlist(
                    SQLUtil.getOffset(limitVO.getCurPage(), limitVO.getLimit()),
                    limitVO.getLimit(),
                    teacherId,
                    sql);
            total = leaveService.teacherGetAmount(sql);
            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list,total);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
        }
    }

    /**
     * 获取数据列表
     * 系领导查询数据接口
     *
     * @return
     */
    @RequestMapping(value = "leaderSlist", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage leaderSlist(@ModelAttribute LeaveModel m, @ModelAttribute LimitVO limitVO) {
        Class clazz = m.getClass();
        // 获取被调用对象的名称
        getInvokeObjName();
        Object[] values = getParameters(clazz, m);
        String[] names = getParameterNames(clazz, m);

        List<LeaveModel> list = null;
        Long total;
        String sql = SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values);
        try {
            // 条件查询
            list =  leaveService.leaderSlist(
                    SQLUtil.getOffset(limitVO.getCurPage(), limitVO.getLimit()),
                    limitVO.getLimit(),
                    sql);
            total = leaveService.leaderGetAmount(sql);
            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list,total);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
        }
    }
}
