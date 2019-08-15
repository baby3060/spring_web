package com.mvc.service.exceptions;

public class MemberDuplicateException extends RuntimeException {
    public MemberDuplicateException() {
        this("중복되는 회원이 존재합니다.");
    }

    public MemberDuplicateException(String msg) {
        super(msg);
    }

    public MemberDuplicateException(String msg, Throwable t) {
        super(msg, t);
    }
}