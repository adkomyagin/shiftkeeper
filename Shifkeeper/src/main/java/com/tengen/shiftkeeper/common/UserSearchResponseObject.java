package com.tengen.shiftkeeper.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="users")
public class UserSearchResponseObject {

	List<UserObject> users = new ArrayList<UserObject>();

	@XmlElement(name = "user")
	public void setUsers(List<UserObject> users) {
		this.users = users;
	}
	
	public List<UserObject> getUsers() {
		return users;
	}

}
