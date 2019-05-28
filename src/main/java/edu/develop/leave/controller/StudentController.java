package edu.develop.leave.controller;

import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.controller.vo.LimitVO;
import edu.develop.leave.model.StudentModel;
import edu.develop.leave.controller.baseController.BaseController;
import edu.develop.leave.service.StudentService;
import edu.develop.leave.utils.ConditionStr;
import edu.develop.leave.utils.LogUtil;
import edu.develop.leave.utils.SQLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/8
 * @since jdk8
 */
@RestController
@RequestMapping("/student")
@Slf4j
@Api(value = "学生接口",description = "学生基本接口")
public class StudentController  extends BaseController<StudentModel> {


    @Autowired
     StudentService studentService;

    @RequestMapping(value = "stuDeletes", method = RequestMethod.DELETE)
    public RespondsMessage stuDeletes(@ApiParam("用户id数组") @RequestParam Long[] ids) {
        getInvokeObjName();
        Integer status;
        try {
            status = studentService.stuDeletes(ids);
            return RespondsMessage.success(LogUtil.logInfo(log, "批量删除数据执行成功"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "用户{}批量删除数据据执行失败：{}", e.getMessage()));
        }
    }

     /**
     *获取数据列表
     * @return
      */
//    @RequestMapping(value = "listByConDition", method = RequestMethod.GET)
//    @ResponseBody
//    public RespondsMessage listByConDition(@ModelAttribute StudentModel m, @ModelAttribute LimitVO limitVO) {
//        System.out.println("controller......");
//        System.out.println(m.toString());
//        System.out.println(limitVO.toString());
//        Class clazz = m.getClass();
//        // 获取被调用对象的名称
//        getInvokeObjName();
//        Object[] values = getParameters(clazz, m);
//        String[] names = studentService.getParameterNames(clazz, m);
//
//        List<Model> list = null;
//        try {
//            // 查询数据列表
//            list = (List<Model>) reflectEngine.invokeMapperMethod(
//                    "list",
//                    invokeObjName,
//                    new Class[]{Integer.class, Integer.class, String.class},
//                    limitVO.getCurPage(),
//                    limitVO.getLimit(),
//                    SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values));
//
//            // 查询数据条数
//            Long amount = (Long)reflectEngine.invokeMapperMethod(
//                    "queryAmount",
//                    invokeObjName,
//                    new Class[]{String.class},
//                    SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values));
//
//            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list,amount);
//        } catch (Exception e) {
//            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
//        }
//    }


}
