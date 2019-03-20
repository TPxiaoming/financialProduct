package com.xiaoming.manager.error;

/**
 * 错误种类
 */
public enum ErrorEnum {
    ID_NOT_NULL("100000","编号不可为空",false),
    UNKONWN("200000","未知异常",false);

    private String errorCode;
    private String message;
    private boolean canRetry;

    ErrorEnum(String errorCode, String message, boolean canRetry){
        this.errorCode = errorCode;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static  ErrorEnum getByCode(String errorCode){
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (errorEnum.message.equals(errorCode)){
                return errorEnum;
            }
        }
        return UNKONWN;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}
