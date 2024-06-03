package com.zhouqi.demoauthsecurity.exception;

import com.zhouqi.demoauthsecurity.common.BaseException;
import com.zhouqi.demoauthsecurity.common.Status;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }
}
