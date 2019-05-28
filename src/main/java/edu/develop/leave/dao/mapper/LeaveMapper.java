package edu.develop.leave.dao.mapper;


import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.LeaveModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 请假条接口
 */
@Mapper
public interface LeaveMapper extends BaseMapper<LeaveModel> {

    /**
     * 老师查询请假条
     * @return
     */
    List<LeaveModel> teacherSlist(@Param("offset") Integer offset,
                                  @Param("limit") Integer limit,
                                  @Param("teacherId") Integer teacherId,
                                  @Param("conditions") String conditions,
                                  @Param("endIndex") Integer endIndex);

    /**
     * 系领导查询请假条
     * @return
     */
    List<LeaveModel> leaderSlist(@Param("offset") Integer offset,
                                 @Param("limit") Integer limit,
                                 @Param("conditions") String conditions,
                                  @Param("startIndex") Integer startIndex,
                                 @Param("endIndex") Integer endIndex);

    /**
     * 老师查询数据条数
     * @param sql
     * @return
     */
    Long teacherGetAmount(@Param("sql") String sql,
                          @Param("endIndex") Integer endIndex);

    /**
     * 领导查询数据条数
     * @param sql
     * @return
     */
    Long leaderGetAmount(@Param("sql") String sql,
                         @Param("startIndex") Integer startIndex,
                         @Param("endIndex") Integer endIndex);
}