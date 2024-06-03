package com.zhouqi.demoauthsecurity.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private Integer code;
    private String message;
    private Object data;

    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse ofSuccess() {
        return ofSuccess(null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(Status.SUCCESS, data);
    }

    public static ApiResponse ofMessage(String message) {
        return of(Status.SUCCESS.getCode(), message, null);
    }

    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    public static ApiResponse ofStatus(IStatus status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t) {
        return of(t.getCode(), t.getMessage(), t.getData());
    }
}
