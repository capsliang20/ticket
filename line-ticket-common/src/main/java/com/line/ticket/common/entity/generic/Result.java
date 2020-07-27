package com.line.ticket.common.entity.generic;

import com.line.ticket.common.entity.constant.ResultConstant;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 该类为服务间接口交互结果类, 仅提供工厂方法用于生成结果,ret的值建议在{@link ResultConstant}中定义。
 *
 * @param <T> 附带data数据的类型
 */
@Getter
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -380872328153407227L;

    private final T data;

    private final String msg;

    private final int ret;

    private Result(T data, String msg, int ret) {
        this.data = data;
        this.msg = msg;
        this.ret = ret;
    }

    private static final Result SIMPLE_SUCCESS_RESULT = new Result<>(null, ResultConstant.EMPTY_MSG, ResultConstant.SUCCESS);

    private static final Result SIMPLE_FAIL_RESULT = new Result<>(null, ResultConstant.EMPTY_MSG, ResultConstant.DEFAULT_FAIL);

    public static Result success() {
        return SIMPLE_SUCCESS_RESULT;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, ResultConstant.EMPTY_MSG, ResultConstant.SUCCESS);
    }

    public static Result fail() {
        return SIMPLE_FAIL_RESULT;
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(null, msg, ResultConstant.DEFAULT_FAIL);
    }

    public static <T> Result<T> fail(String msg, int ret) {
        return new Result<>(null, msg, ret);
    }
}
