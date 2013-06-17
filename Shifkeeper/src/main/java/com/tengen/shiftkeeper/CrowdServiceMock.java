package com.tengen.shiftkeeper;

import java.util.ArrayList;

public class CrowdServiceMock {
	private ArrayList<UserObject> data;
	
	public void addByEmail(String email) {
		UserObject user = new UserObject("0xFACE" + email, email);
		data.add(user);
	}
	
	public ArrayList<UserObject> getUsers() {
		return data;
	}
	
	public CrowdServiceMock() {
		data = new ArrayList<UserObject>();
	}
}
