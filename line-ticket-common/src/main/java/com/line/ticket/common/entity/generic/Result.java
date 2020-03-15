package com.line.ticket.common.entity.generic;

import com.line.ticket.common.entity.constant.ResultConstant;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -380872328153407227L;

    private T data;

    private String msg;

    private Integer ret;

    private Result(T data, String msg, Integer ret) {
        this.data = data;
        this.msg = msg;
        this.ret = ret;
    }

    public static <T> Result success() {
        return new Result(null, ResultConstant.EMPTY_MSG, ResultConstant.COMMON_SUCCESS_CODE);
    }

    public static <T> Result success(T data) {
        return new Result(data, ResultConstant.EMPTY_MSG, ResultConstant.COMMON_SUCCESS_CODE);
    }

    public static <T> Result success(T data, String msg) {
        return new Result(data, msg, ResultConstant.COMMON_SUCCESS_CODE);
    }

    public static <T> Result fail() {
        return new Result(null, ResultConstant.EMPTY_MSG, ResultConstant.COMMON_FAIL_CODE);
    }

    public static <T> Result fail(T data) {
        return new Result(data, ResultConstant.EMPTY_MSG, ResultConstant.COMMON_FAIL_CODE);
    }

    public static <T> Result fail(String msg) {
        return new Result(null, msg, ResultConstant.COMMON_FAIL_CODE);
    }

    public static <T> Result fail(T data, @NonNull Integer ret) {
        return new Result(data, ResultConstant.EMPTY_MSG, ret);
    }

    public static <T> Result fail(String msg, @NonNull Integer ret) {
        return new Result(null, msg, ret);
    }


    public static <T> Result fail(T data, String msg) {
        return new Result(data, msg, ResultConstant.COMMON_FAIL_CODE);
    }

    public static <T> Result fail(T data, String msg, @NonNull Integer ret) {
        return new Result(data, msg, ret);
    }
}
