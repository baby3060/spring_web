package com.mvc.service;

import com.mvc.entity.UserInfo;

public interface AuthService {
    public UserInfo authenticate(int memberSeq, String password);
}