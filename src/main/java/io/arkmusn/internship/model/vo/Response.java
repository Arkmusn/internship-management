package io.arkmusn.internship.model.vo;

/**
 * 响应实体
 *
 * @author Arkmusn
 *         create 2017/11/18
 */

public class Response<T> {
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_FAIL = "400";

    private boolean success;
    private String code;
    private T data;

    public Response() {
        this(true, CODE_SUCCESS, null);
    }

    public Response(boolean success) {
        this(success, CODE_SUCCESS, null);
        if (!success)
            this.code = CODE_FAIL;
    }

    public Response(T data) {
        this(true, null, data);
    }

    public Response(boolean success, T data) {
        this(success, CODE_SUCCESS, data);
    }

    public Response(boolean success, String code) {
        this(success, code, null);
    }

    public Response(boolean success, String code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
