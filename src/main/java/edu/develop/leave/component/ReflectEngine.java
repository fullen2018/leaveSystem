package edu.develop.leave.component;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @version 1.0
 * @anthor  on 2019/3/30
 * @since jdk8
 */
@Component
public class ReflectEngine {

    /**
     * 反射执行执行dao方法
     * @param methodName 需要执行的dao方法名
     * @param clazzs 方法参数对象组类类型（为了反射时找到具体方法）
     * @param objects 调用方法参数组（执行方法需要的参数）
     * @return 返回方法执行结果
     * @throws Exception 抛出反射执行过程中出现的异常
     * */
    public Object invokeMapperMethod(String methodName,String objectName,Class[] clazzs, Object... objects) throws Exception {
        try {
            // 从容器中获取bean的实例
            Object o = SpringContextHolder.getBean(objectName);
            // 根据实例获取对象的类类型
            Class<?> clazz = o.getClass();
            // 根据方法名和参数的类类型获取方法对象
            Method method = clazz.getMethod(methodName, clazzs);
            return method.invoke(o,objects);
        }catch (Exception e){
            throw e;
        }
    }
}
