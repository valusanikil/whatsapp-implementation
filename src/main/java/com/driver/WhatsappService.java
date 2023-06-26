package com.driver;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

	WhatsappRepository whatsappRepository=new WhatsappRepository();
	
	public String createUser(String name, String mobile) throws UserAlreadyExistsException {
		return whatsappRepository.createUser(name, mobile);
	}

	public Group createGroup(List<User> users) throws InvalidGroupException {
		return whatsappRepository.createGroup(users);
	}

	public int createMessage(String content) {
		return whatsappRepository.createMessage(content);
	}

	public int sendMessage(Message message, User sender, Group group) throws GroupDoesNotExistException, InvalidSenderException {
		return whatsappRepository.sendMessage(message, sender, group);
	}

	public String changeAdmin(User approver, User user, Group group) throws GroupDoesNotExistException, ApproverDoesNotHaveRightsException, UserIsNotAParticipantException {
		return whatsappRepository.changeAdmin(approver, user, group);
	}

	public int removeUser(User user) {
		return whatsappRepository.removeUser(user);
	}

	public String findMessage(Date start, Date end, int k) throws GreaterException {
		return whatsappRepository.findMessage(start, end, k);
	}
	
}
