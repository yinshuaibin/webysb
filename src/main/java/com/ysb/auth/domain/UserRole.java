package com.ysb.auth.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserRole {
    /** 自增ID*/
    private Integer id;
    /** 用户ID*/
    private Integer userId;
    /** 角色ID*/
    private String roleId;

    public UserRole() {
    }

    public UserRole(Integer id, Integer userId, String roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
