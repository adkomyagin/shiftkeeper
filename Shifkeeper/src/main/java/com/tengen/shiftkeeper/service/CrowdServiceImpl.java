package com.tengen.shiftkeeper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.tengen.shiftkeeper.common.UserObject;
import com.tengen.shiftkeeper.common.UserSearchResponseObject;
import com.tengen.shiftkeeper.exceptions.CrowdException;

public class CrowdServiceImpl implements CrowdService {
	private static final Logger logger = LoggerFactory.getLogger(CrowdService.class);
	
	private RestTemplate restTemplate;
	private String crowdUrl;

	public void setCrowdUrl(String crowdUrl) {
		this.crowdUrl = crowdUrl;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public UserObject getUserByEmail(String email) throws CrowdException {
		try {
			UserSearchResponseObject results = (UserSearchResponseObject) restTemplate.getForObject(crowdUrl + "/search?entity-type=user&restriction={param}", UserSearchResponseObject.class,"email=" + email);
			if (results.getUsers().size() == 0)
				return null;
			
			for(int i=0; i< results.getUsers().size(); i++)
			{
				logger.info("Found user for " + email + " : {}", results.getUsers().get(i).getName());
			}
			
			return results.getUsers().get(0);
		} catch(Exception e) {
			//e.printStackTrace();
			throw new CrowdException(e.getMessage());
		}
	}

	@Override
	public void addUserToGroup(UserObject user, String groupName)
			throws CrowdException {
		try {
			restTemplate.postForObject(crowdUrl + "/group/user/direct?groupname={param}", user, UserObject.class, groupName);
			
		} catch(Exception e) {
			//e.printStackTrace();
			throw new CrowdException(e.getMessage());
		}
	}

	@Override
	public void removeUserFromGroup(UserObject user, String groupName)
			throws CrowdException {
		try {
			restTemplate.delete(crowdUrl + "/group/user/direct?groupname={param}&username={param}",groupName, user.getName());
			
		} catch(Exception e) {
			//e.printStackTrace();
			throw new CrowdException(e.getMessage());
		}
	}

	@Override
	public List<UserObject> getGroupMembers(String groupName)
			throws CrowdException {
		
		try {
			UserSearchResponseObject results = (UserSearchResponseObject) restTemplate.getForObject(crowdUrl + "/group/user/direct?groupname={param}", UserSearchResponseObject.class, groupName);
			
			for(int i=0; i< results.getUsers().size(); i++)
			{
				fillUserInfo(results.getUsers().get(i));
			}
			
			return results.getUsers();
		} catch(Exception e) {
			//e.printStackTrace();
			throw new CrowdException(e.getMessage());
		}
	}
	
	private void fillUserInfo(UserObject user) {
		UserObject tmp = (UserObject) restTemplate.getForObject(crowdUrl + "/user?username={param}", UserObject.class, user.getName());
		user.setDisplayName(tmp.getDisplayName());
		user.setEmail(tmp.getEmail());
	}

}
