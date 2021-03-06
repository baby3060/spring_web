package com.mvc.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import lombok.*;

@EqualsAndHashCode
public class Member {
    private int memberSeq;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String loginType = "1";
    private String[] favoriteFoods;
    private boolean allowMail = true;
    private LocalDateTime registDate;
    private String mobile;
    
    private Level userLevel;
    
    public LocalDateTime getRegistDate() {
        return registDate;
    }

    public void setRegistDate(LocalDateTime registDate) {
        this.registDate = registDate;
    }


    public String toString() {
        String favoStr = "";
        if( favoriteFoods != null ) {
            favoStr = String.join(",", this.favoriteFoods);
        }
        return "[" + this.name + "], email : " + this.email + ", LoginType : " + loginType + ", Favorite Foods Is " + favoStr;
    }

    public boolean equals(Object obj) {
        if( obj == null ) {
            return false;
        } else {
            if( obj instanceof Member ) {
                if( obj == this ) {
                    return true;
                } else {
                    Member temp = (Member) obj;

                    return (this.memberSeq == temp.getMemberSeq()) &&  this.name.equals(temp.getName()) && this.email.equals(temp.getEmail()) && this.password.equals(temp.getPassword()) && this.loginType.equals(temp.getLoginType());
                }
            } else {
                return false;
            }
        }
    }

    public int hashCode() {
        int result = 31;
        result += this.memberSeq;
        result += this.name.hashCode();
        result += this.email.hashCode();
        result += this.password.hashCode();
        result += this.loginType.hashCode();
        return result;
    }

    public int getMemberSeq() { return this.memberSeq;}
    public void setMemberSeq(int memberSeq) { this.memberSeq = memberSeq; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getLoginType() { return loginType; }
    public void setLoginType(String loginType) { this.loginType = loginType; }

    public String[] getFavoriteFoods() { return favoriteFoods; }
    public void setFavoriteFoods(String[] favoriteFoods) { this.favoriteFoods = favoriteFoods; }

    public boolean getAllowMail() { return allowMail; }
    public void setAllowMail(boolean allowMail) { this.allowMail = allowMail; }

    public Level getUserLevel() { return userLevel; }
    public void setUserLevel(Level userLevel) { this.userLevel = userLevel; }
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}