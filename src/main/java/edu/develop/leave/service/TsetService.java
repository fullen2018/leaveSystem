package edu.develop.leave.service;

import edu.develop.leave.dao.mapper.TsetMapper;
import edu.develop.leave.model.TsetModel;
import edu.develop.leave.service.baseService.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/17
 * @since jdk8
 */
@Service
public class TsetService extends BaseService<TsetModel> {

    @Resource
    TsetMapper tsetMapper;

    /**
     * 更新设置
     * @param totalDays
     * @param teacherDays
     * @return
     */
    public Integer updateSet(Integer totalDays,Integer teacherDays){
         return  tsetMapper.updateSet(totalDays,teacherDays);
    }

    /**
     * 查询最近五条公告
     *
     * @return
     */
    public List<TsetModel> queryNotice(){
        return  tsetMapper.queryNotice();
    }
}
