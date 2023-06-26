package com.driver;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public String handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidGroupException.class)
	public String handleInvalidGroupException(InvalidGroupException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(GroupDoesNotExistException.class)
	public String handleGroupDoesNotExistException(GroupDoesNotExistException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(InvalidSenderException.class)
	public String handleInvalidSenderException(InvalidSenderException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(ApproverDoesNotHaveRightsException.class)
	public String handleApproverDoesNotHaveRightsException(ApproverDoesNotHaveRightsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(UserIsNotAParticipantException.class)
	public String handleUserIsNotAParticipantException(UserIsNotAParticipantException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(GreaterException.class)
	public String handleGreaterException(GreaterException ex) {
		return ex.getMessage();
	}
}
