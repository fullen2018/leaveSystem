package edu.develop.leave.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LeaveModel implements Serializable {

    private Integer leaveId = null;

    private Long studentId = null;

    private String cause;

    private String startTime;

    private String endTime;

    private Integer status;

    private Integer totalDay;

    private String handlerName;

    private String handlerRole;

    private String handlerTime;

    private StudentModel student;
}