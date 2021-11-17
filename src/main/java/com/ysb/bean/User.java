package com.ysb.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yinshuaibin
 * @date 2021/3/19 11:55
 * @description
 */
@Data
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
}
