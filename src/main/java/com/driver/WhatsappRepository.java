package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

@Repository
public class WhatsappRepository {

	// Assume that each user belongs to at most one group
	// You can use the below mentioned hashmaps or delete these and create your own.
	private HashMap<Group, List<User>> groupUserMap;
	private HashMap<Group, List<Message>> groupMessageMap;
	private HashMap<Message, User> senderMap;
	private HashMap<Group, User> adminMap;
	private HashSet<String> userMobile;
	private List<User> userDetails;
	private HashMap<Integer, String> message;
	private int customGroupCount;
	private int messageId;

	public WhatsappRepository() {
		this.groupMessageMap = new HashMap<Group, List<Message>>();
		this.groupUserMap = new HashMap<Group, List<User>>();
		this.senderMap = new HashMap<Message, User>();
		this.adminMap = new HashMap<Group, User>();
		this.userMobile = new HashSet<>();
		this.userDetails = new ArrayList<User>();
		this.message = new HashMap<Integer, String>();
		this.customGroupCount = 0;
		this.messageId = 0;
	}

	public String createUser(String name, String mobile) throws UserAlreadyExistsException {
		User user = new User(name, mobile);
		if (userMobile.contains(mobile)) {
			throw new UserAlreadyExistsException("User Already Exists");
		}

		else {
			userMobile.add(mobile);
			userDetails.add(user);
			return "SUCCESS";
		}
	}

	public Group createGroup(List<User> users) throws InvalidGroupException {
		if (users.size() < 2) {
			throw new InvalidGroupException("Minimum users to create a group is 2");
		}

		if (users.size() == 2) {
			Group newGroup = new Group(users.get(1).getName(), 2);
			groupUserMap.put(newGroup, users);
			adminMap.put(newGroup, users.get(0));
			return newGroup;
		}

		else {
			customGroupCount++;
			Group newGroup = new Group("Group " + customGroupCount, users.size());
			groupUserMap.put(newGroup, users);
			adminMap.put(newGroup, users.get(0));
			return newGroup;
		}
	}

	public int createMessage(String content) {
		messageId++;
		message.put(messageId, content);
		return messageId;
	}

	public int sendMessage(Message message, User sender, Group group)
			throws GroupDoesNotExistException, InvalidSenderException {
		if (!groupUserMap.containsKey(group)) {
			throw new GroupDoesNotExistException("Group Does Not Exist");
		}

		else if (!senderMap.containsValue(sender)) {
			throw new InvalidSenderException("You are not allowed to send message");
		}

		List<User> users = groupUserMap.get(group);
		List<Message> messageList = groupMessageMap.get(group);
		for (User user : users) {
			if (sender.equals(user)) {
				if (groupMessageMap.containsKey(group)) {
					messageList.add(message);
					groupMessageMap.put(group, messageList);
					messageId++;
				}
			}
		}
		return messageList.size();
	}

	public String changeAdmin(User approver, User user, Group group)
			throws GroupDoesNotExistException, ApproverDoesNotHaveRightsException, UserIsNotAParticipantException {
		if (!groupUserMap.containsKey(group)) {
			throw new GroupDoesNotExistException("Group Does Not Exist");
		}
		if (!adminMap.get(group).equals(approver)) {
			throw new ApproverDoesNotHaveRightsException("Approver does not have rights");
		}
		for (User users : groupUserMap.get(group)) {
			if (!users.equals(user)) {
				throw new UserIsNotAParticipantException("User is not a participant");
			}
		}
		adminMap.put(group, user);
		return "SUCCESS";
	}

	public int removeUser(User user) {
		for (Group group : groupUserMap.keySet()) {
			for (User users : groupUserMap.get(group)) {
				if (users.equals(user)) {
					for (User admin : adminMap.values()) {
						if (admin.equals(user)) {
							return -2;
						}
					}
					for (Message message : senderMap.keySet()) {
						if (senderMap.get(message).equals(user)) {
							senderMap.remove(message);
							groupMessageMap.get(group).remove(message);
							userDetails.remove(user);
						}
						groupUserMap.get(group).remove(user);
						group.setNumberOfParticipants(group.getNumberOfParticipants() - 1);
						return messageId + groupMessageMap.get(group).size() + groupUserMap.get(group).size();
					}
				}
			}
		}
		return -1;
	}

	public String findMessage(Date start, Date end, int k) throws GreaterException {
		if(message.size()<k){
			throw new GreaterException("K is greater than the number of messages");
		}
		else {
			return message.get(k);
		}
	}
}
