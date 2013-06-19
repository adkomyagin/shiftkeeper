package com.tengen.shiftkeeper.common;

public class RequestContextObject {

	private UserObject user;
	private GroupObject group;
	
	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}

	public GroupObject getGroup() {
		return group;
	}

	public void setGroup(GroupObject group) {
		this.group = group;
	}

	public RequestContextObject() {
		user = new UserObject();
		group = new GroupObject();
	}

}
