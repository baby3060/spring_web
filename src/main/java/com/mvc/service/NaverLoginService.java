package com.mvc.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface NaverLoginService {
	
	/**
	 * 네이버 아이디 로그인 호출 URL 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public String getAuthorizationUrl(HttpSession session, String authType) throws Exception;
	
	/**
	 * 서비스를 이용하기 위한 토큰 발급(ACCESS_TOKEN, REFRESH_TOKEN의 경우 만료를 대비 보관해두기)
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getAccessToken(String code, String state) throws Exception;
	
	/**
	 * 토큰 갱신
	 * @param refresh_token
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getRefreshAccessToken(String refreshToken) throws Exception;
	
	/**
	 * 네이버 회원 동의한 프로필 가지고 오기
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getProfile(String accessToken) throws Exception;
	
	
	/**
	 * 해당 토큰 유효성 체크하기
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getVerifyAccessToken(String accessToken) throws Exception;
	
	
	/**
	 * 네아로 연동 해제 => 회원 탈퇴 시 진행
	 * 연동 테이블도 삭제 할 것
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> releasePerist(String accessToken) throws Exception;
}