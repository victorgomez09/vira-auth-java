package com.virasoftware.common.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.virasoftware.common.exception.UnprocessableEntityException;

@Aspect
public class ValidationAspect {

    @Pointcut("@annotation(com.virasoftware.common.aspects.ValidateErrors)")
    public void validateResultMethod() {}

    @After("validateResultMethod() && args(..,result)")
    public void afterMethodWithResult(BindingResult result) {
        validateErrors(result);
    }

    private void validateErrors(BindingResult result) {
        if(result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getDefaultMessage()).append("\n");
            }
            throw new UnprocessableEntityException(errorMessage.toString());
        }
    }

}
