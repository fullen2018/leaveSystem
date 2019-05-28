package edu.develop.leave.dao.mapper;

import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.TeacherModel;
import edu.develop.leave.model.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 教师接口
 */
@Mapper
public interface TeacherMapper extends BaseMapper<TeacherModel> {
    /**
     * 教师登录
     * @param no  职工号
     * @param password 密码
     * @return
     */
    Token login(@Param("no") Integer no, @Param("password") String password);
}