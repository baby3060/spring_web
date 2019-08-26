package com.mvc.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePwdValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
    }
}