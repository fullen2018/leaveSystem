package edu.develop.leave.service;

import edu.develop.leave.dao.mapper.StudentMapper;
import edu.develop.leave.model.StudentModel;
import edu.develop.leave.service.baseService.BaseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static edu.develop.leave.controller.baseController.BaseController.toDatabaseField;

/**
 * @version 1.0
 * @anthor on 2019/5/8
 * @since jdk8
 */
@Service
public class StudentService extends BaseService<StudentModel> {

    @Resource
    StudentMapper studentMapper;

    /**
     * 多向删除
     * @param ids
     * @return
     */
    public int stuDeletes(Long[] ids){
        return studentMapper.stuDeletes(ids);
    }

    /**
     * 获取有值的字段名
     * @param clazz
     * @param m
     * @return
     */
    public String[] getParameterNames(Class clazz, StudentModel m) {
        ArrayList<String> arrayList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            try {
                if (f.get(m) != null &&
                        !f.get(m).equals(0) &&
                        !f.getName().equals("serialVersionUID")&&
                        !f.get(m).equals("")) {
                    // 获取域的名称
                    String name = toDatabaseField(f.getName());
                    if(name.equals("class_id")){
                        name = "student."+name;
                    }
                    arrayList.add(name);
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

}
