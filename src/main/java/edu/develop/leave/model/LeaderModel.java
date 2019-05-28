package edu.develop.leave.model;

import java.io.Serializable;

public class LeaderModel implements Serializable {
    private Integer leaderId = null;

    private Integer roleId = null;

    private String leaderName;

    private String leaderPassword;

    private static final long serialVersionUID = 1L;

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPassword() {
        return leaderPassword;
    }

    public void setLeaderPassword(String leaderPassword) {
        this.leaderPassword = leaderPassword;
    }
}