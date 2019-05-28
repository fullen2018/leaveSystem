package edu.develop.leave.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author zhangpei
 * @version 1.0
 * @description 响应客户端的信息
 * @date 2019/4/20
 */
@Data
@Builder
@NoArgsConstructor
public class RespondsMessage<D> {
    public static final Integer SUCCESS_CODE = 200;

    public static final Integer CLIENT_ERROR = 400;
    public static final Integer UNAUTHORIZED = HttpStatus.UNAUTHORIZED.value();
    public static final Integer SERVER_ERROR = 500;

    private D data;

    private D amount;

    /**
     * 基础展示视图
     */
    public interface BaseView {
    }

    @JsonView(value = BaseView.class)
    private Integer code;

    @JsonView(value = BaseView.class)
    private String msg;

    public RespondsMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespondsMessage(D data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public RespondsMessage(D data, D amount, Integer code, String msg) {
        this.data = data;
        this.amount = amount;
        this.code = code;
        this.msg = msg;
    }

    public static RespondsMessage success(String msg) {
        return new RespondsMessage(SUCCESS_CODE, msg);
    }

    public static <D> RespondsMessage success(String msg, D data,D amount) {
        return new RespondsMessage<>(data,amount, SUCCESS_CODE, msg);
    }

    public static <D> RespondsMessage success(String msg, D data) {
        return new RespondsMessage<>(data, SUCCESS_CODE, msg);
    }

    public static RespondsMessage failure(Integer code, String msg) {
        return new RespondsMessage(code, msg);
    }

    public static RespondsMessage failure(String msg) {
        return new RespondsMessage(CLIENT_ERROR, msg);
    }

    public static RespondsMessage failurePermission(String msg) {
        return new RespondsMessage(UNAUTHORIZED, msg);
    }

    public static <D> RespondsMessage failure(String msg, D data) {
        return new RespondsMessage<>(data, CLIENT_ERROR, msg);
    }

    public static RespondsMessage clientError(String msg) {
        return failure(CLIENT_ERROR, msg);
    }

    public static RespondsMessage serverError(String msg) {
        return failure(SERVER_ERROR, msg);
    }
}
