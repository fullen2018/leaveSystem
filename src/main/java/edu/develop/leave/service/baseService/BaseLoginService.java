package edu.develop.leave.service.baseService;

import edu.develop.leave.dao.mapper.LeaderMapper;
import edu.develop.leave.dao.mapper.StudentMapper;
import edu.develop.leave.dao.mapper.TeacherMapper;
import edu.develop.leave.model.LeaderModel;
import edu.develop.leave.model.StudentModel;
import edu.develop.leave.model.TeacherModel;
import edu.develop.leave.model.Token;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @version 1.0
 * @anthor on 2019/5/7
 * @since jdk8
 */
@Service
public class BaseLoginService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    LeaderMapper leaderMapper;

    public Token login(Long no,String password,Integer roleType){
        Token token = null;
        switch (roleType){

            case 0: token = studentMapper.login(no,password);break;//学生登录
            case 1: token = teacherMapper.login(no.intValue(),password);break;//教师登录
            case 2: token = leaderMapper.login(no.intValue(),password);break;//系领导登录
            default:break;

        }
        return token;
    }

    /**
     * 学生、老师、系领导更改密码
     * @param password
     * @param id
     * @param role
     * @return
     */
    public Integer resetPassword(String password, Long id, Integer role){
        Integer status;
        if(role == 1){
            StudentModel studentModel = new StudentModel();
            studentModel.setStudentId(id);
            studentModel.setStudentPassword(password);
            status = studentMapper.updateByPrimaryKeySelective(studentModel);
        }else if (role == 2){
            TeacherModel teacherModel = new TeacherModel();
            teacherModel.setTeacherId(id.intValue());
            teacherModel.setTeacherPassword(password);
            status = teacherMapper.updateByPrimaryKeySelective(teacherModel);
        }else  if(role == 3){
            LeaderModel leaderModel = new LeaderModel();
            leaderModel.setLeaderId(id.intValue());
            leaderModel.setLeaderPassword(password);
            status = leaderMapper.updateByPrimaryKeySelective(leaderModel);
        }else {
            return 0;
        }

        return status;
    }
}
