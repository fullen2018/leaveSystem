package edu.develop.leave.dao.mapper;

import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.StudentModel;
import edu.develop.leave.model.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学生接口
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentModel> {

    /**
     * 学生登录接口
     * @param no  学号
     * @param password 密码
     * @return
     */
    Token login(@Param("no") Long no,@Param("password") String password);

    /**
     *多相删除
     * @param ids
     * @return
     */
     int stuDeletes(@Param("ids") Long[] ids);
}