package com.mvc.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mvc.command.LoginCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
    private final String EMAIL_REG_EXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    public LoginValidator() {
        pattern = Pattern.compile(EMAIL_REG_EXP);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LoginCommand command = (LoginCommand)target;

        if( command.getEmail() == null || command.getEmail().trim().isEmpty() ) {
            errors.rejectValue("email", "required");
        } else {
            Matcher matcher = pattern.matcher(command.getEmail());
            if(!matcher.matches()) {
                errors.rejectValue("email", "bad");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "required");
    }
}