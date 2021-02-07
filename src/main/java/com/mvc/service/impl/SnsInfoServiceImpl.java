package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.entity.SnsInfo;
import com.mvc.repository.SnsInfoRepository;
import com.mvc.service.SnsInfoService;

@Service("snsInfoService")
public class SnsInfoServiceImpl implements SnsInfoService {
	
	@Autowired
	private SnsInfoRepository snsInfoRepository;
	
	@Override
	public int countByMobile(String mobile, String snsGubun) {
		// TODO Auto-generated method stub
		return snsInfoRepository.countByMobile(mobile.replaceAll("-", ""), snsGubun);
	}

	@Override
	public int insertSInfo(SnsInfo info) {
		return snsInfoRepository.insertSInfo(info);
	}

	@Override
	public SnsInfo selectSInfoByMobile(String mobile, String snsGubun) {
		// TODO Auto-generated method stub
		return snsInfoRepository.selectSInfoByMobile(mobile.replaceAll("-", ""), snsGubun);
	}

}
