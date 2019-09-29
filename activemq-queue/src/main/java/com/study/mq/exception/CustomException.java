package com.study.mq.exception;

/**
 * 自定义异常
 *
 * @author shiboxue
 * @since 2019/9/12
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
