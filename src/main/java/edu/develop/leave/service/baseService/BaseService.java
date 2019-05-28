package edu.develop.leave.service.baseService;

import edu.develop.leave.component.ReflectEngine;
import edu.develop.leave.utils.SQLUtil;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * service基础类
 *
 * @param <T>
 */
@Transactional(rollbackFor = Exception.class)
public class BaseService<T> {

    @Resource
    private ReflectEngine reflectEngine;

    // 被调用对象的名字
    protected String invokeObjName;

    /**
     * 添加一条记录
     *
     * @param t
     * @throws Exception
     */
    public Integer add(T t) throws Exception {
        getInvokeObjName();
        return (Integer) reflectEngine.invokeMapperMethod("insertSelective", invokeObjName, new Class[]{Object.class}, t);
    }

    /**
     * 批量删除用户
     *
     * @param ids id数组
     * @return 数据库影响行数
     * @throws Exception 删除失败抛出异常
     */
    public Integer deletes(Integer[] ids) throws Exception {
        getInvokeObjName();
        return (Integer) reflectEngine.invokeMapperMethod("deletes", invokeObjName, new Class[]{Integer[].class}, new Object[]{ids});
    }

    /**
     * 在数据库中删除一个对象
     *
     * @param id 需要删除对象的id值
     * @return 数据库影响行数
     * @throws Exception 删除失败抛出异常
     */
    public Integer delete(Integer id) throws Exception {
        getInvokeObjName();
        return (Integer) reflectEngine.invokeMapperMethod("deleteByPrimaryKey", invokeObjName, new Class[]{Integer.class}, id);
    }

    /**
     * 更新数据库中某个对象的相应字段
     *
     * @param t 需要更新的对象
     * @return
     * @throws Exception 修改失败抛出异常
     */
    public Integer update(T t) throws Exception {
        System.out.println("service:"+t.toString());
        getInvokeObjName();
        return (Integer) reflectEngine.invokeMapperMethod("updateByPrimaryKeySelective", invokeObjName, new Class[]{Object.class}, t);
    }


    /**
     * 查找某个相应条件的对象列表
     *
     * @param page          列表分页页数
     * @param size          当前页需要查询对象的最大数量
     * @param conditionsSql 查找列表时的sql条件  sql语=语句里where后面的部分都写在改字符串里
     * @return 返回符合条件的对象列表 但查找失败时返回null
     */
    public List<T> list(Integer page, Integer size, String conditionsSql) {
        getInvokeObjName();
        try {
            System.out.println("-----------------:"+page+",size:"+size+"getOffset:"+SQLUtil.getOffset(page, size));
            Object find = reflectEngine.invokeMapperMethod("queryByCondition", invokeObjName, new Class[]{Integer.class, Integer.class, String.class},
                    SQLUtil.getOffset(page, size), size, conditionsSql);
            return (List<T>) find;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据条件查询数据条数
     *
     * @param conditionsSql 查找列表时的sql条件  sql语=语句里where后面的部分都写在改字符串里
     * @return
     */
    public Long queryAmount(String conditionsSql) {
        getInvokeObjName();
        try {
            Object findAmount = reflectEngine.invokeMapperMethod("queryAmount", invokeObjName, new Class[]{String.class}, conditionsSql);
            return (Long) findAmount;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据唯一id值查找某个对象
     *
     * @param id 查找的id值
     * @return 返回查找到的对象 查找失败返回空值
     */
    public T findById(Integer id) {
        getInvokeObjName();
        try {
            Object find = reflectEngine.invokeMapperMethod("selectByPrimaryKey", invokeObjName, new Class[]{Integer.class}, id);
            return (T) find;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取被调用对象的名字
     */
    protected void getInvokeObjName() {
        invokeObjName = this.getClass().getSimpleName();
        invokeObjName = invokeObjName.replace("Service", "Mapper");
        invokeObjName = invokeObjName.substring(0, 1).toLowerCase() + invokeObjName.substring(1);
    }
}
