package edu.develop.leave.model;

import io.swagger.models.auth.In;
import lombok.Data;
import java.io.Serializable;

@Data
public class TeacherModel implements Serializable {

    /**
     * 老师的工作号
     */
    private Integer teacherId = null;

    /**
     * 班级id
     */
    private Integer classId = null;

    /**
     * 班级
     */
    private ClassModel clazz = null;

    /**
     * 角色id
     */
    private Integer roleId = null;


    /**
     * 老师名称
     */
    private String teacherName;

    /**
     * 老师登录密码
     */
    private String teacherPassword;

    public TeacherModel() {
    }

    private static final long serialVersionUID = 1L;

    public TeacherModel(Integer classId, Integer roleId, String teacherName, String teacherPassword) {
        this.classId = classId;
        this.roleId = roleId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }
}