package edu.develop.leave.controller;

import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.controller.vo.LimitVO;
import edu.develop.leave.model.TsetModel;
import edu.develop.leave.service.TsetService;
import edu.develop.leave.utils.ConditionStr;
import edu.develop.leave.utils.LogUtil;
import edu.develop.leave.utils.SQLUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/15
 * @since jdk8
 *
 * 设置接口
 */
@RestController
@RequestMapping("tset")
@Api(value = "设置接口")
@Slf4j
public class TsetController extends BaseController<TsetModel> {

    @Autowired
    TsetService tsetService;

    /**
     * 获取数据列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @Override
    public RespondsMessage list(@ModelAttribute TsetModel m, @ModelAttribute LimitVO limitVO) {
        System.out.println("controller......");
        System.out.println(m.toString());
        System.out.println(limitVO.toString());
        Class clazz = m.getClass();
        // 获取被调用对象的名称
        getInvokeObjName();
        Object[] values = getParameters(clazz, m);
        String[] names = getParameterNames(clazz, m);

        List<Model> list = null;
        try {
            // 查询数据列表
            list = (List<Model>) reflectEngine.invokeMapperMethod(
                    "list",
                    invokeObjName,
                    new Class[]{Integer.class, Integer.class, String.class},
                    limitVO.getCurPage(),
                    limitVO.getLimit(),
                    SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values));
            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list);
        } catch (Exception e) {
            e.printStackTrace();
            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
        }
    }

    /**
     * 更新设置
     *
     * @param maxDays
     * @param teacherDays
     * @return
     */
    @RequestMapping(value = "update/set", method = RequestMethod.PUT)
    public RespondsMessage update(@RequestParam("maxDays")Integer maxDays,@RequestParam("teacherDays")Integer teacherDays) {

        getInvokeObjName();
        Integer status = 0;
        try {
            status = tsetService.updateSet(maxDays,teacherDays);
            System.out.println("-----------------"+status);
            if(status == 1){
                return RespondsMessage.success(LogUtil.logInfo(log, "设置成功"), status);
            }
            return RespondsMessage.failure(LogUtil.logInfo(log, "设置失败"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "用户{}更新数据执行失败：{}", e.getMessage()), status);
        }
    }

    @RequestMapping(value = "notices",method = RequestMethod.GET)
    public RespondsMessage  queryNotice(){
       return RespondsMessage.success("",tsetService.queryNotice());
    }
}
