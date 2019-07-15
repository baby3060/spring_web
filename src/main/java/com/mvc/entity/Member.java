package com.mvc.entity;

import lombok.*;

@EqualsAndHashCode
public class Member {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String loginType = "1";
    private String[] favoriteFoods;
    private boolean allowMail = true;

    public Member() {}
    public Member(String name, String email, String password, String loginType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
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

                    return this.name.equals(temp.getName()) && this.email.equals(temp.getEmail()) && this.password.equals(temp.getPassword()) && this.loginType.equals(temp.getLoginType());
                }
            } else {
                return false;
            }
        }
    }

    public int hashCode() {
        int result = 31;
        result += this.name.hashCode();
        result += this.email.hashCode();
        result += this.password.hashCode();
        result += this.loginType.hashCode();
        return result;
    }

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
}