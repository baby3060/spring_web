package com.mvc.service.exceptions;

public class IDNotMatchingException extends RuntimeException {
    public IDNotMatchingException() {
        this("입력한 EMAIL과 PASSWORD과 매핑되지 않습니다.");
    }

    public IDNotMatchingException(String msg) {
        super(msg);
    }

    public IDNotMatchingException(String msg, Throwable t) {
        super(msg, t);
    }
}