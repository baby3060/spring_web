package com.mvc.repository;

public interface SnsConfigRepository {
	public String getClientId(String code);
	
	public String getApiKey(String code);
}
