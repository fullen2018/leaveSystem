package edu.develop.leave.service;

import edu.develop.leave.controller.vo.LimitVO;
import edu.develop.leave.dao.mapper.LeaveMapper;
import edu.develop.leave.model.LeaveModel;
import edu.develop.leave.model.TsetModel;
import edu.develop.leave.service.baseService.BaseService;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @anthor on 2019/5/8
 * @since jdk8
 */
@Service
public class LeaveService extends BaseService<LeaveModel> {

    @Resource
    LeaveMapper leaveMapper;

    @Autowired
    TsetService tsetService;

    /**
     * 老师查询
     * @param offset
     * @param limit
     * @param teacherId
     * @param conditions
     * @return
     */
   public List<LeaveModel> teacherSlist(Integer offset, Integer limit,Integer teacherId, String conditions){
       return leaveMapper.teacherSlist(offset,limit,teacherId,conditions,getLimit(1).get(0));
   }

    /**
     * 系领导查询
     * @param offset
     * @param limit
     * @param conditions
     * @return
     */
    public List<LeaveModel> leaderSlist(Integer offset, Integer limit, String conditions){
        List<Integer> sectiones = getLimit(2);
        return leaveMapper.leaderSlist(offset,limit,conditions,sectiones.get(0)+1,sectiones.get(1));
    }

    /**
     * 老师查询数据条数
     * @param sql
     * @return
     */
    public Long teacherGetAmount(String sql){
       return  leaveMapper.teacherGetAmount(sql,getLimit(1).get(0));
    }

    /**
     * 学生查询数据条数
     * @param sql
     * @return
     */
    public Long leaderGetAmount(String sql){
        List<Integer> sectiones = getLimit(2);
        return  leaveMapper.leaderGetAmount(sql,sectiones.get(0)+1,sectiones.get(1));
    }

    /**
     * 获取不同角色不同审核时间区间
     * @param role
     * @return
     */
    private List<Integer> getLimit(Integer role){
        List<TsetModel> list = tsetService.list(0,10,null);
        List<Integer> resultList = new ArrayList<>();
        Integer max = 0;
        Integer min = 0;
        for (TsetModel t:
             list) {
            if(role == 1 && t.getType() == 3){
                resultList.add(Integer.valueOf(t.getContent()));
                return  resultList;
            }else if(t.getType() == 2){
                max = Integer.valueOf(t.getContent());
            }else if(t.getType() == 3){
                min = Integer.valueOf(t.getContent());
            }
        }
        resultList.add(min);
        resultList.add(max);
        return  resultList;
    }


}
