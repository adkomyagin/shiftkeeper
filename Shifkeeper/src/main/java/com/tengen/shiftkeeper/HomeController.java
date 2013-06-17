package com.tengen.shiftkeeper;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CrowdService crowdService;
	
	@RequestMapping(value = "/")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view;
		
		view = new ModelAndView("home");
		
		if( request.getParameter("add")!=null ) {
			logger.info("Adding {}", request.getParameter("email"));
			try {
				UserObject user = crowdService.getUserByEmail(request.getParameter("email"));
				if (user != null)
				{
					logger.info("Detected name: {}", user.getName());
					crowdService.addUserToGroup(user, "support@10gen.com");
				}
			} catch (CrowdException e) {
				logger.info("Oh shit: {}", e.getMessage());
			}
			
        }
		
		if( request.getParameter("remove")!=null ) {
			logger.info("Removing {}", request.getParameter("name"));
//			try {
//				UserObject user = crowdService.getUserByEmail("DD");
//				//if (user != null)
//				//{
//				//	logger.info("Detected name: {}", "DD");
//				//	crowdService.addUserToGroup(user, "support@10gen.com");
//				//}
//			} catch (CrowdException e) {
//				logger.info("Oh shit: {}", e.getMessage());
//			}
			
        }
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		
		view.addObject("serverTime", formattedDate );
		try {
			view.addObject("users", crowdService.getGroupMembers("support@10gen.com") );
		} catch (CrowdException e) {
			logger.info("Oh shit: {}", e.getMessage());
		}
		
		return view;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		String[] arr = new String[2];
		arr[0] = "A";
		arr[1] = "B";
		model.addAttribute("arr", arr );
		
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home1(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		if (model.containsAttribute("add"))
				logger.info(":S:S:S");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		String[] arr = new String[2];
		arr[0] = "A";
		arr[1] = "B";
		model.addAttribute("arr", arr );
		
		return "home";
	}*/
}
