package com.mvc.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.repository.SnsConfigRepository;
import com.mvc.service.NaverLoginService;

@Service("naverLoginService")
public class NaverLoginServiceImpl implements NaverLoginService {

	@Autowired
	private SnsConfigRepository snsConfigRepository;

	private String makeRandomString() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	private final String NAVER_AUTH_URL = "https://nid.naver.com/oauth2.0/authorize";

	private final String NAVER_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
	
	private final String NAVER_PROFILE_URL = "https://openapi.naver.com/v1/nid/me";
	
	private final String NAVER_VERIFY_ACCESS_TOKEN = "https://openapi.naver.com/v1/nid/verify";
	
	private String getClientKey() {
		return snsConfigRepository.getClientId("NAV");
	}

	private final String getApiKey() {
		return snsConfigRepository.getApiKey("NAV");
	}

	private static HttpURLConnection makeConnect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);

			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String getRequest(String apiUrl) {
		return getRequest(apiUrl, null);
	}

	private static String getRequest(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = makeConnect(apiUrl);

		String result = "";

		try {
			con.setRequestMethod("GET");

			if (requestHeaders != null && !requestHeaders.isEmpty()) {
				for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}
			}

			int responseCode = con.getResponseCode();

			result = readBody(con.getInputStream());

		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
		return result;
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		// try resource
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	@Override
	public String getAuthorizationUrl(HttpSession session, String authType) throws Exception {
		
		
		StringBuffer reqBf = new StringBuffer();
		reqBf.append("?client_id=" + getClientKey());
		reqBf.append("&response_type=code");
		reqBf.append("&redirect_uri="
				+ URLEncoder.encode("http://localhost:8383/spring_web/login/naver/login_callback", "UTF-8"));
		
		String stateString = makeRandomString();
		setSession(session, stateString);
		reqBf.append("&state=" + stateString);
		
		if( !(authType == null || "".equals(authType)) ) {
			reqBf.append("&auth_type=" + authType);
		} 
		
		// TODO Auto-generated method stub
		// https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트
		// 아이디}&response_type=code&redirect_uri={개발자 센터에 등록한 콜백 URL(URL 인코딩)}&state={상태
		// 토큰}
		
		String returnUrl = NAVER_AUTH_URL + reqBf.toString();
		
		return returnUrl;
	}

	@Override
	public Map<String, Object> getAccessToken(String code, String state) throws Exception {
		StringBuffer requestTokenUrlParam = new StringBuffer();

		requestTokenUrlParam.append("?grant_type=authorization_code");
		requestTokenUrlParam.append("&code=" + code);
		requestTokenUrlParam.append("&state=" + state);
		requestTokenUrlParam.append("&client_id=" + getClientKey());
		requestTokenUrlParam.append("&client_secret=" + getApiKey());
		requestTokenUrlParam.append("&redirect_uri="
				+ URLEncoder.encode("http://localhost:8383/spring_web/login/naver/login_callback", "UTF-8"));

		return jsonParsing(getRequest(NAVER_TOKEN_URL + requestTokenUrlParam.toString()));
	}

	@Override
	public Map<String, Object> getProfile(String accessToken) throws Exception {
		// TODO Auto-generated method stub
		
		String header = "Bearer " + accessToken;
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = getRequest(NAVER_PROFILE_URL,requestHeaders);
		
		return jsonParsing(responseBody);
	}

	@Override
	public Map<String, Object> getRefreshAccessToken(String refreshToken) throws Exception {

		StringBuffer requestTokenUrlParam = new StringBuffer();
		
		requestTokenUrlParam.append("?grant_type=refresh_token");
		requestTokenUrlParam.append("&client_id=" + getClientKey());
		requestTokenUrlParam.append("&client_secret=" + getApiKey());
		requestTokenUrlParam.append("&refresh_token=" + refreshToken);
		
		return jsonParsing(getRequest(NAVER_TOKEN_URL + requestTokenUrlParam.toString()));
	}
	
	@Override
	public Map<String, Object> getVerifyAccessToken(String accessToken) throws Exception {
		String header = "Bearer " + accessToken;
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = getRequest(NAVER_VERIFY_ACCESS_TOKEN + "?info=true", requestHeaders);
		
		return jsonParsing(responseBody);
	}
	
	
	@Override
	public Map<String, Object> releasePerist(String accessToken) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuffer requestTokenUrlParam = new StringBuffer();
		
		requestTokenUrlParam.append("?grant_type=delete");
		requestTokenUrlParam.append("&client_id=" + getClientKey());
		requestTokenUrlParam.append("&client_secret=" + getApiKey());
		requestTokenUrlParam.append("&refresh_token=" + accessToken);
		
		return jsonParsing(getRequest(NAVER_TOKEN_URL + requestTokenUrlParam.toString()));
	}
	
	
	private void setSession(HttpSession session, String strState) {
		if(session.getAttribute("state") != null) {
			session.removeAttribute("state");
		}
		
		session.setAttribute("state", strState);
	}

	private static Map<String, Object> jsonParsing(String jsonString) {
		ObjectMapper mapper = new ObjectMapper(); 
		try { 
			Map<String, Object> map = mapper.readValue(jsonString, Map.class); 
			return map; 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		return null;
	}
	
}
