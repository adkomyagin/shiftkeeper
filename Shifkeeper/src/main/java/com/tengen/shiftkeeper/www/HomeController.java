package com.tengen.shiftkeeper.www;

import java.security.KeyStore.Entry;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tengen.shiftkeeper.common.GroupObject;
import com.tengen.shiftkeeper.common.UserObject;
import com.tengen.shiftkeeper.exceptions.CrowdException;
import com.tengen.shiftkeeper.service.CrowdService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final static String USER_ATTRIBUTE_NAME = "aUser";
	private final static String GROUP_ATTRIBUTE_NAME = "aGroup";
	private final static Map<String, String> JIRA_groups;
	
	static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("support", "support@10gen.com");
        aMap.put("support_us", "support-us@10gen.com");
        JIRA_groups = Collections.unmodifiableMap(aMap);
    }
	
	@Autowired
	private CrowdService crowdService;
	
	@RequestMapping(value = "/")
	public ModelAndView handleRequest(@ModelAttribute(value = USER_ATTRIBUTE_NAME) UserObject aUser, @ModelAttribute(value = GROUP_ATTRIBUTE_NAME) GroupObject aGroup, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("home");
		String group = JIRA_groups.get(aGroup.getName());
		logger.info("Ololo " + request.getParameter("group"));
		
		group = JIRA_groups.get(request.getParameter("group"));
		if( request.getParameter("add")!=null ) {
			logger.info("Adding {} to " + group, request.getParameter("email"));
			try {
				UserObject user = crowdService.getUserByEmail(request.getParameter("email"));
				if (user != null)
				{
					logger.info("Detected name: {}", user.getName());
					crowdService.addUserToGroup(user, group);
				}
			} catch (CrowdException e) {
				logger.info("Oh shit: {}", e.getMessage());
				view.addObject("error", e.getMessage() );
			}
			
        }
		
		if( request.getParameter("remove")!=null ) {
			logger.info("Removing {} from " + group, aUser.getName());
			try {
				crowdService.removeUserFromGroup(new UserObject("ivan", null), group);
			} catch (CrowdException e) {
				logger.info("Oh shit: {}", e.getMessage());
				view.addObject("error", e.getMessage() );
			}
			
        }
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		view.addObject("serverTime", formattedDate );
		
		Iterator<java.util.Map.Entry<String, String>> iter = JIRA_groups.entrySet().iterator();
		while(iter.hasNext())
		{
			java.util.Map.Entry<String, String> entry = iter.next();
			try {
				view.addObject(entry.getKey(), crowdService.getGroupMembers(entry.getValue()) );
			} catch (CrowdException e) {
				logger.info("Oh shit: {}", e.getMessage());
				view.addObject("error", e.getMessage() );
			}
		}
		
		return view;
	}
}
