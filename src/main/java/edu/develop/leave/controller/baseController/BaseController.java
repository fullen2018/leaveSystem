package edu.develop.leave.controller.baseController;

import edu.develop.leave.component.ReflectEngine;
import edu.develop.leave.controller.dto.RespondsMessage;
import edu.develop.leave.utils.ConditionStr;
import edu.develop.leave.utils.LogUtil;
import edu.develop.leave.utils.SQLUtil;
import edu.develop.leave.controller.vo.LimitVO;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @anthor zsl on 2019/3/30
 * @since jdk8
 * <p>
 * controller的基础类，通过反射去执行service
 */
@Slf4j
public class BaseController<M> {

    @Resource
    protected ReflectEngine reflectEngine;

    protected String invokeObjName;

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */

    @RequestMapping(value = "deletes", method = RequestMethod.DELETE)
    @ResponseBody
    public RespondsMessage deletes(@ApiParam("用户id数组") @RequestParam Integer[] ids) {
        getInvokeObjName();
        Integer status;
        try {
            status = (Integer) reflectEngine.invokeMapperMethod("deletes",
                    invokeObjName,
                    new Class[]{Integer[].class},
                    new Object[]{ids});
            return RespondsMessage.success(LogUtil.logInfo(log, "批量删除数据执行成功"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "用户{}批量删除数据据执行失败：{}", e.getMessage()));
        }
    }

    /**
     * 删除一条数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RespondsMessage delete(@ApiParam("用户id") @RequestParam Integer id) {
        getInvokeObjName();
        Integer status = null;
        try {
            status = (Integer) reflectEngine.invokeMapperMethod("delete", invokeObjName, new Class[]{Integer.class}, id);
            return RespondsMessage.success(LogUtil.logInfo(log, "用户{}删除一条数据据执行成功"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "用户{}删除一条数据据执行失败：{}", e.getMessage()), status);
        }
    }

    /**
     * 添加一条数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public RespondsMessage add(@ModelAttribute M model) {
        getInvokeObjName();
        Integer status = 0;
        try {
            status = (Integer) reflectEngine.invokeMapperMethod("add", invokeObjName, new Class[]{Object.class}, model);
            return RespondsMessage.success(LogUtil.logInfo(log, "添加一条数据执行成功"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "添加一条数据执行失败：{}", e.getMessage()), status);
        }
    }

    /**
     * 更新一条数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public RespondsMessage update(@ModelAttribute M model) {
        System.out.println("controller:"+model.toString());
        getInvokeObjName();
        Integer status = 0;
        try {
            status = (Integer) reflectEngine.invokeMapperMethod("update", invokeObjName, new Class[]{Object.class}, model);
            return RespondsMessage.success(LogUtil.logInfo(log, "用户{}更新数据执行成功"), status);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "用户{}更新数据执行失败：{}", e.getMessage()), status);
        }
    }

    /**
     * 根据条件查询数据条数
     *
     * @return
     */
    @RequestMapping(value = "queryAmount", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage queryAmount(@ModelAttribute M m) {
        Map<String, Object> map = new HashMap<>();
        Long account = 0L;
        getInvokeObjName();
        Class clazz = m.getClass();
        Object[] values = getParameters(clazz, m);
        String[] names = getParameterNames(clazz, m);
        try {
            account = (Long) reflectEngine.invokeMapperMethod(
                    "queryAmount",
                    invokeObjName,
                    new Class[]{String.class},
                    SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values));
            return RespondsMessage.success(LogUtil.logInfo(log, "根据条件查询数据条数执行成功"), account);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "根据条件查询数据条数执行失败"), account);
        }
    }

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage findById(@ApiParam("用户id") @RequestParam Integer id) {
        Object m = null;
        getInvokeObjName();
        try {
            m = reflectEngine.invokeMapperMethod("findById", invokeObjName, new Class[]{Integer.class}, id);
            return RespondsMessage.success(LogUtil.logInfo(log, "根据id查询一条数据执行成功"), m);
        } catch (Exception e) {
            return RespondsMessage.failure(LogUtil.logInfo(log, "根据id查询一条数据执行失败"));
        }
    }

    /**
     * 获取数据列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public RespondsMessage list(@ModelAttribute M m, @ModelAttribute LimitVO limitVO) {
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

            // 查询数据条数
            Long amount = (Long)reflectEngine.invokeMapperMethod(
                    "queryAmount",
                    invokeObjName,
                    new Class[]{String.class},
                    SQLUtil.fillCondition(ConditionStr.sqlTemplate(names), values));

            return RespondsMessage.success(LogUtil.logInfo(log, "获取数据列表执行成功"), list,amount);
        } catch (Exception e) {
            e.printStackTrace();
            return RespondsMessage.failure(LogUtil.logInfo(log, "获取数据列表执行失败"));
        }
    }

    /**
     * 获取被调用对象的名字
     */
    protected void getInvokeObjName() {
        invokeObjName = this.getClass().getSimpleName();
        invokeObjName = invokeObjName.replace("Controller", "Service");
        invokeObjName = invokeObjName.substring(0, 1).toLowerCase() + invokeObjName.substring(1);
    }

    /**
     * 通过反射获取参数值
     *
     * @param clazz
     * @return
     */
    protected Object[] getParameters(Class clazz, M m) {
        ArrayList arrayList = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            try {
                if (f.get(m) != null &&
                        !f.getName().equals("serialVersionUID") &&
                        !f.get(m).equals("")) {
                    // 获取域的值
                    arrayList.add(f.get(m));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Object[] values = new Object[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            values[i] = arrayList.get(i);
        }

        return values;
    }

    /**
     * 获取参数域的名称
     *
     * @param clazz
     * @param m
     * @return
     */
    protected String[] getParameterNames(Class clazz, M m) {
        ArrayList<String> arrayList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            try {
                if (f.get(m) != null &&
                        !f.getName().equals("serialVersionUID")&&
                        !f.get(m).equals("")) {
                    // 获取域的名称
                    arrayList.add(toDatabaseField(f.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        String[] values = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            values[i] = arrayList.get(i);
        }
        return values;
    }


    /**
     * 将驼峰式编程用下滑线隔开的词语
     * @param name
     * @return
     */
    public static  String toDatabaseField(String name){
           int index = -1;
           for(int i = 0; i < name.length(); i++){
               if(name.charAt(i)>= 'A' && name.charAt(i) <= 'Z'){
                   index = i;
               }
           }
           if(index == -1){
               return name;
           }

           name = name.substring(0,index)+"_"+name.substring(index,name.length());
           return name.toLowerCase();
    }

}
