package com.mvc.repository;

import com.mvc.entity.SnsInfo;

public interface SnsInfoRepository {
	public int countByMobile(String mobile, String snsGubun);
	public int insertSInfo(SnsInfo snsInfo);
	public SnsInfo selectSInfoByMobile(String mobile, String snsGubun);
}
