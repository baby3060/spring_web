package com.mvc.entity;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SnsInfo {
	private int snsId;
	private String snsName;
	private String snsEmail;
	private String snsMobile;
	private int memberSeq;
	private String snsGubun;
	public int getSnsId() {
		return snsId;
	}
	public void setSnsId(int snsId) {
		this.snsId = snsId;
	}
	public String getSnsName() {
		return snsName;
	}
	public void setSnsName(String snsName) {
		this.snsName = snsName;
	}
	public String getSnsEmail() {
		return snsEmail;
	}
	public void setSnsEmail(String snsEmail) {
		this.snsEmail = snsEmail;
	}
	public String getSnsMobile() {
		return snsMobile;
	}
	public void setSnsMobile(String snsMobile) {
		this.snsMobile = snsMobile;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getSnsGubun() {
		return snsGubun;
	}
	public void setSnsGubun(String snsGubun) {
		this.snsGubun = snsGubun;
	}
}
