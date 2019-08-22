package com.mvc.service.exceptions;

public class NotMatchingMemberException extends RuntimeException {
    public NotMatchingMemberException() {
        this("입력한 email과 일치하는 멤버가 없습니다.");
    }

    public NotMatchingMemberException(String msg) {
        super(msg);
    }

    public NotMatchingMemberException(String msg, Throwable t) {
        super(msg, t);
    }
}