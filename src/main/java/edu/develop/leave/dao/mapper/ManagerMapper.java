package edu.develop.leave.dao.mapper;


import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.ManagerModel;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员接口
 */
public interface ManagerMapper  extends BaseMapper<ManagerModel> {

    /**
     * 管理员登录
     * @param id
     * @param password
     * @return
     */
     Integer login(@Param("id") Integer id,@Param("password") String password);
}