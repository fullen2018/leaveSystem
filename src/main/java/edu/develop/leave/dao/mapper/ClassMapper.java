package edu.develop.leave.dao.mapper;

import edu.develop.leave.dao.mapper.base.BaseMapper;
import edu.develop.leave.model.ClassModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 班级接口
 */
@Mapper
public interface ClassMapper extends BaseMapper<ClassModel> {

    public List<ClassModel> findAllClass();
}