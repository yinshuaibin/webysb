package com.ysb.auth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserLogin {

    private int id;
    private String userId;
    private String userName;
    private String loginIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
    private String loginReason;

}
