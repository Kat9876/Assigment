package com.assignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.assignment.model.ErrorInfo;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@Autowired
	protected MessageSource messageSource;
	
	
	@ExceptionHandler({ CustomException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public Object handleCustomException(HttpServletRequest request, CustomException ex) {
		return new ErrorInfo(ex.getErrorCode(), messageSource.getMessage(ex.getErrorCode(), null,
				"Unable to process request", LocaleContextHolder.getLocale()));
	}
	

}
