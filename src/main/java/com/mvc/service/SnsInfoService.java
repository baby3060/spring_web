package com.mvc.service;

import com.mvc.entity.SnsInfo;

public interface SnsInfoService {
	public int countByMobile(String mobile, String snsGubun);
	public int insertSInfo(SnsInfo info);
	public SnsInfo selectSInfoByMobile(String mobile, String snsGubun);
}
