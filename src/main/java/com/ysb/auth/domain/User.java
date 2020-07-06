package com.ysb.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
public class User implements Cloneable {

    /** 用户ID*/
    private String userId;
   /** 用户名*/
    private String username;
    /** 昵称*/
    private String nickname;
    /** 登录ip*/
    private String loginIp;
    /** 密码*/
    private String password;
    /** 起初登录时间*/
    private Date dtCreate;
    /** 最后登录时间*/
    private Date lastLogin;

    /** 区域名称*/
    private String areaName;
    /** 绩效*/
    private Integer scores;
    /** 邮件*/
    private String email;
    /** 电话号码*/
    private String telephone;
    /** 办公电话*/
    private String officePhone;
    /** 是否可用*/
    private int enabled;
    /** 账户是否过期*/
    private Boolean accountNonExpired;
    /** 账户是否锁定*/
    private Boolean accountNonLocked;
    /** 过期是否认证*/
    private Boolean credentialsNonExpired;
    /** 角色ID*/
    private String roleId;
    //用户对应的角色
//    private Role role;
    private String roleName;
    private String roleDesc;

    private String idCard;
    private String salt;

    /** 用户创建时间 */
    private String createTime;
    /** 用户到期时间 */
    private String overTime;
    
    /** 配置文件中的ip地址及端口 */
    private String commonIp;

   /**
    * 此字段为#
    */
   private String  jnumber;

    /**
     * 用户注册时的人脸base64, root用户不需要
     */
   private String face;

    /**
     * 用户登录时是否需要验证人脸
     */
   private boolean needCompare;

    //重写Object类的clone方法
    @Override
    public Object clone() {
        Object obj=null;
        //调用Object类的clone方法，返回一个Object实例
        try {
            obj= super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

   @JsonIgnore
   public String getCredentialsSalt(){
    return username+salt;
   }
}