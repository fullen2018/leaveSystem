package edu.develop.leave.model;

import java.io.Serializable;

public class ClassModel implements Serializable {
    private Integer classId = null;

    private Integer teacherId = null;

    private Integer gradeId = null;

    private String className = null;

    private static final long serialVersionUID = 1L;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "classId=" + classId +
                ", teacherId=" + teacherId +
                ", gradeId=" + gradeId +
                ", className='" + className + '\'' +
                '}';
    }
}