package edu.develop.leave.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class StudentModel implements Serializable {
    private Long studentId = null;

    private Integer roleId = null;

    private Integer classId = null;

    private String studentName = null;

    private String studentPassword = null;

    private ClassModel classModel;

    private static final long serialVersionUID = 1L;

}