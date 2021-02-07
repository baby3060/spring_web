package com.mvc.entity;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserInfo {
	
	public UserInfo(int memberSeq, String email, String name) {
		this.memberSeq = memberSeq;
		this.email = email;
		this.name = name;
	}
	
    private int memberSeq;
    private String email;
    private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}