package edu.develop.leave.model;

import lombok.Data;

/**
 * @version 1.0
 * @anthor on 2019/5/7
 * @since jdk8
 */
@Data
public class Token {

    // 学号或者职工号
    private Long no;

    // 名字
    private String name;

    // 角色名称
    private String roleName;

    // 角色id
    private Integer roleId;
}
