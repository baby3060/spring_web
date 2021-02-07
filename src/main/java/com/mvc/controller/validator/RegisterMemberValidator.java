package com.mvc.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mvc.entity.Member;

public class RegisterMemberValidator implements Validator {
    private final String EMAIL_REG_EXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    public RegisterMemberValidator() {
        pattern = Pattern.compile(EMAIL_REG_EXP);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member)target;

        if( member.getEmail() == null || member.getEmail().trim().isEmpty() ) {
            errors.rejectValue("email", "required");
        } else {
            Matcher matcher = pattern.matcher(member.getEmail());
            if(!matcher.matches()) {
                errors.rejectValue("email", "bad");
            }
        }
        
        // 휴대전화 패턴도 체크해야 하지만 귀찮으니까 PASS
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginType", "required");
        
        if( !member.getPassword().isEmpty() ) {
            if( !member.getPassword().equals(member.getConfirmPassword()) ) {
                errors.rejectValue("confirmPassword", "nomatch");
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }
}