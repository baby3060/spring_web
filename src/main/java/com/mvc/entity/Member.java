package com.mvc.entity;

import lombok.*;

@EqualsAndHashCode
public class Member {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    
    public Member() {}
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Member(String name, String email, String password, String confirmPassword) {
        this(name, email, password);
        this.confirmPassword = confirmPassword;
    }

    public String toString() {
        return "[" + this.name + "], email : " + this.email;
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

                    return this.name.equals(temp.getName()) && this.email.equals(temp.getEmail()) && this.password.equals(temp.getPassword());
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

}