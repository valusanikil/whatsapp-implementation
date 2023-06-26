package com.driver;

public class UserIsNotAParticipantException extends Exception{
	public UserIsNotAParticipantException(String message) {
		super(message);
	}
}
