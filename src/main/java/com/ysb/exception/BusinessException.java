package com.ysb.exception;

/**
 * Created by Cheng on 2017/6/21.
 * Description: 自定义业务层异常,需要自定义业务代码
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 业务代码
     */
    private int bizCode;


    /**
     * 错误代码
     */
    private String message;

    public BusinessException(String bizType, int bizCode, String message){
        super(message);
        this.bizType = bizType;
        this.bizCode = bizCode;
        this.message = message;
    }

    public BusinessException(String message){
        super(message);
        this.bizType = "";
        this.bizCode = -1;
        this.message = message;
    }

    public BusinessException(String bizType, String message){
        super(message);
        this.bizType = bizType;
        this.bizCode = -1;
        this.message = message;
    }

    public BusinessException(int bizCode, String message){
        super(message);
        this.bizType = "";
        this.bizCode = bizCode;
        this.message = message;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
