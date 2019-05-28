package edu.develop.leave.dao.mapper;

import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.LeaderModel;
import edu.develop.leave.model.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系领导接口
 */
@Mapper
public interface LeaderMapper extends BaseMapper<LeaderModel> {
    /**
     * 系领导登录
     * @param no  职工号
     * @param password 密码
     * @return
     */
    Token login(@Param("no") Integer no, @Param("password") String password);
}