package com.app.profile.controller;

import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.app.profile.dto.resp.ResourceNotFoundException;
import com.app.profile.dto.resp.RestErrorInfo;


public class AbstractRestHandler implements ApplicationEventPublisherAware {

	protected ApplicationEventPublisher eventPublisher;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataFormatException.class)
	public @ResponseBody RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request,
			HttpServletResponse response) {
		return new RestErrorInfo(ex, "You messed up.");
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;

	}

}
