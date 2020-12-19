package com.mvc.command;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginCommand {
    private int memberSeq;
    private String email;
    private String password;
    // Email 기억할지 쿠키
    private boolean rememberEmail;
    
    public int getMemberSeq() {
        return memberSeq;
    }
    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isRememberEmail() {
        return rememberEmail;
    }
    public void setRememberEmail(boolean rememberEmail) {
        this.rememberEmail = rememberEmail;
    }

}