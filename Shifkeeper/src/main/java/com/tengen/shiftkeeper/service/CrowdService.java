package com.tengen.shiftkeeper.service;

import java.util.List;

import com.tengen.shiftkeeper.common.UserObject;
import com.tengen.shiftkeeper.exceptions.CrowdException;

public interface CrowdService {
	public UserObject getUserByEmail(String email) throws CrowdException;
	public void addUserToGroup(UserObject user, String groupName) throws CrowdException;
	public void removeUserFromGroup(UserObject user, String groupName) throws CrowdException;
	public List<UserObject> getGroupMembers(String groupName) throws CrowdException;
}
