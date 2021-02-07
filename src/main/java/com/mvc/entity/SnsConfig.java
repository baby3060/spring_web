package com.mvc.entity;

/**
 * API KEY 매핑(DB에서 보관) 
 */
public class SnsConfig {
	private String code;
	private String apiKey;
	private String apiClient;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApiClient() {
		return apiClient;
	}
	public void setApiClient(String apiClient) {
		this.apiClient = apiClient;
	}
	
}
