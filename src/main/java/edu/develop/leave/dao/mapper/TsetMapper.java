package edu.develop.leave.dao.mapper;


import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.TsetModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设置接口
 */
public interface TsetMapper extends BaseMapper<TsetModel> {

    /**
     * 更新设置
     * @param totalDays
     * @param teacherDays
     * @return
     */
     Integer updateSet(@Param("totalDays") Integer totalDays,@Param("teacherDays") Integer teacherDays);

    /**
     * 查询最近五条公告
     *
     * @return
     */
    List<TsetModel> queryNotice();
}