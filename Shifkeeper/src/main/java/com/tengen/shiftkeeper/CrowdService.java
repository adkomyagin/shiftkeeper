package com.tengen.shiftkeeper;

import java.util.List;

public interface CrowdService {
	public UserObject getUserByEmail(String email) throws CrowdException;
	public void addUserToGroup(UserObject user, String groupName) throws CrowdException;
	public void removeUserFromGroup(UserObject user, String groupName) throws CrowdException;
	public List<UserObject> getGroupMembers(String groupName) throws CrowdException;
}
