package com.mvc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.controller.validator.LoginValidator;
import com.mvc.command.LoginCommand;
import com.mvc.entity.Member;
import com.mvc.entity.SnsInfo;
import com.mvc.entity.UserInfo;
import com.mvc.service.AuthService;
import com.mvc.service.MemberService;
import com.mvc.service.NaverLoginService;
import com.mvc.service.SnsInfoService;
import com.mvc.service.exceptions.IDNotMatchingException;
import com.mvc.service.exceptions.NotMatchingMemberException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	private AuthService authService;

	private NaverLoginService naverLoginService;
	
	private MemberService memberService; 
	
	private SnsInfoService snsInfoService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	};

	@Autowired
	public void setNaverLoginService(NaverLoginService naverLoginService) {
		this.naverLoginService = naverLoginService;
	};

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	};
	
	@Autowired
	public void setSnsInfoService(SnsInfoService snsInfoService) {
		this.snsInfoService = snsInfoService;
	};
	
	@GetMapping
	public String form(LoginCommand command, @CookieValue(value = "REMEMBER_EMAIL", required = false) Cookie cookie,
			Model model, HttpServletRequest request) throws Exception {
		if (cookie != null) {
			command.setEmail(cookie.getValue());
			command.setRememberEmail(true);
		}

		model.addAttribute("url", naverLoginService.getAuthorizationUrl(request.getSession(), ""));
		model.addAttribute("command", command);
		return "login/loginForm";
	}
	
	
	@GetMapping("/naver/login_callback")
	public String joinForm(@RequestParam(required = false) String code, @RequestParam(required = false) String error,
			@RequestParam(required = false) String state, HttpSession session, Model model, HttpServletRequest request) throws Exception {
		
		if ( error == null || "".equals(error) || "null".equals(error)) {
			
			String storedState = (String) session.getAttribute("state");
			
			if ( !state.equals(storedState)) {
				System.out.println("401 Auth");
				return "redirect:" + naverLoginService.getAuthorizationUrl(session, "");
			} else {
				
				try {
					Map<String, Object> tokenResult = naverLoginService.getAccessToken(code, state);
					
					// ERROR일 경우 => 이 경우에는 새로고침
					if (tokenResult.containsKey("error")) {
						String errorCode = (String)tokenResult.get("error");
						
						if( "invalid_request".equals(errorCode) ) {
							return "redirect:" + naverLoginService.getAuthorizationUrl(session, "");
						} 
					} else {
						String accessToken = (String) tokenResult.get("access_token");
						
						// 프로필 정보를 얻을 때, 사용하기
						final String REFRESH_TOKEN = (String) tokenResult.get("refresh_token");
						
						Map<String, Object> verifyResult = naverLoginService.getVerifyAccessToken(accessToken);

						final String VERIFY_RESULT_CODE = (String) verifyResult.get("resultcode");

						boolean isAccess = false;

						// API 사용 가능한지? 토큰 사용 가능한지?
						if ("00".equals(VERIFY_RESULT_CODE)) {
							isAccess = true;
						} else {
							// 
							verifyResult = naverLoginService.getRefreshAccessToken(REFRESH_TOKEN);
							
							if( "00". equals((String) verifyResult.get("resultcode"))) {
								isAccess = true;
								
								accessToken = (String) tokenResult.get("access_token");
								
							} else {
								isAccess = false;
							}
						}

						if (isAccess) {

							Map<String, Object> profileResult = naverLoginService.getProfile(accessToken);
							
							String resultCode = (String)profileResult.get("resultcode");
							
							if( "00".equals(resultCode) ) {
								
								@SuppressWarnings("unchecked")
								Map<String, String> response = (Map<String, String>)profileResult.get("response");
								
								// Mobile이 Null이라면
								if( response.get("mobile") == null || "".equals((String)response.get("mobile"))) {
									System.out.println("Mobile 비어 있음 - 제동의 필요하다고 스크립트를 통해 ALERT 띄우고 아래 URL로 location 이동");
									return "redirect:" + naverLoginService.getAuthorizationUrl(session, "reprompt");
								} else {
									
									String mobile = (String)response.get("mobile");
									String email = (response.get("email") == null) ? "":response.get("email");
									String name = (response.get("name") == null) ? "":response.get("name");
									
									// 핸드폰 번호로 확인하기
									int count = memberService.countByMobile(mobile);
									
									// 회원 테이블에 저장되어 있지 않다면?
									if( count == 0 ) {
										String params = "?mobile=" + (String)response.get("mobile") + "&agree=true&email=" + email + "&name=" + URLEncoder.encode(name, "UTF-8");
										
										model.addAttribute("nextUrl", request.getServletContext().getContextPath() + "/register/step1" + params);
										
										
									// 저장되어 있다면 로그인
									} else {
										// SNS_INFO에 등록된 회원인지 확인(SNS_GUBUN : NAVER, MOBILE로 확인)
										int countSInfo = snsInfoService.countByMobile(mobile, "NAV");
										
										SnsInfo snsInfo = new SnsInfo();
										
										// Mobile을 가지고 Member 조회해오기
										Member member = memberService.selectMemberByMobile(mobile);
										
										if( countSInfo == 0 ) {
											// SNS_INFO 등록
											snsInfo = new SnsInfo();
											snsInfo.setSnsEmail(email);
											snsInfo.setSnsGubun("NAV");
											snsInfo.setSnsMobile(mobile);
											snsInfo.setSnsName(name);
											snsInfo.setMemberSeq(member.getMemberSeq());
											
											snsInfoService.insertSInfo(snsInfo);
										} 
										
										try {
											UserInfo info = authService.authenticate(member.getMemberSeq());
											
											session.setAttribute("info", info);
											model.addAttribute("nextUrl", request.getServletContext().getContextPath() + "/main.do");
										} catch(Exception e) {
											e.printStackTrace();
											model.addAttribute("nextUrl", request.getServletContext().getContextPath() + "/login");
										}
									}
									
									return "login/move";
								}
							} else {
								return "redirect:" + naverLoginService.getAuthorizationUrl(session, "");
							}
						
						// 갱신 토큰도 만료된 상태, 따라서 갱신 새로 받으러 가야 함.
						} else {
							// 재갱신 받으러 가기
							return "redirect:" + naverLoginService.getAuthorizationUrl(session, "");
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
					return "login/move";
				}
			}
		} else {
			System.out.println("error : " + error);
			return "login/move";
		}

		return "redirect:" + naverLoginService.getAuthorizationUrl(session, "");
	}

	@PostMapping
	public String submit(@ModelAttribute("command") LoginCommand command, Errors errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		new LoginValidator().validate(command, errors);

		if (errors.hasErrors()) {
			model.addAttribute("command", command);
			return "login/loginForm";
		}

		try {
			UserInfo info = authService.authenticate(command.getEmail(), command.getPassword());

			Cookie rememberCookie = new Cookie("REMEMBER_EMAIL", command.getEmail());

			rememberCookie.setPath("/");

			if (command.isRememberEmail()) {
				// 한달 간 저장
				rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				rememberCookie.setMaxAge(0);
			}

			response.addCookie(rememberCookie);

			// HttpSession과 Request에서 getSession 가져오는 차이는 HttpSession을 받을 경우 항상 Session 객체
			// 생성

			HttpSession session = request.getSession();
			session.setAttribute("info", info);
			return "login/loginSuccess";
		} catch (IDNotMatchingException idme) {
			model.addAttribute("command", command);
			errors.reject("IDPasswordNotMatching");
			return "login/loginForm";
		} catch (NotMatchingMemberException nme) {
			String[] args = { command.getEmail() };
			model.addAttribute("command", command);
			errors.reject("NOMember", args, "");
			return "login/loginForm";
		}
	}

}