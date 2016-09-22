package com.wdy.com.wdy.exception;

/**
 * Created by wdy on 16/9/22.
 */
public class UserExistExcetion extends Exception {
    public UserExistExcetion() {
        super();
    }

    public UserExistExcetion(String message) {
        super(message);
    }

    public UserExistExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistExcetion(Throwable cause) {
        super(cause);
    }

    protected UserExistExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
