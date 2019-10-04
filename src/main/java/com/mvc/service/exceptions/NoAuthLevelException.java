package com.mvc.service.exceptions;

/**
 * 사용자가 해당 컨텐츠를 이용할 자격(레벨)이 안 될 경우 발생할 예외
 * Advice에서 catch
 * service에서 애노테이션을 통해 정의
 */
public class NoAuthLevelException extends RuntimeException {

}