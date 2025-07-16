package com.cotodel.crm.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cotodel.crm.web.properties.ApplicationConstantConfig;
import com.cotodel.crm.web.response.UserDetailsEntity;
import com.cotodel.crm.web.service.Impl.TokenGenerationImpl;
import com.cotodel.crm.web.util.JwtTokenValidator;

@Controller
@CrossOrigin
public class StaticPageController extends CotoDelBaseController{

	private static final Logger logger = LoggerFactory.getLogger(StaticPageController.class);
	
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	TokenGenerationImpl tokengeneration;

	@GetMapping(value="/")
	public String firstPage(Model model) {
		 return "redirect:/login";
	}	
	
	@GetMapping(value="/login")
	public String loginPage(Model model) {
		String screenName="index";
		return screenName;
	}
//	@GetMapping(value="/FleetLogin")
//	public ModelAndView loginFleet(Model model) {
//		logger.info("opening login Fleet");
//		
//	    String message = (String)session.getAttribute("message");
//	    String mobile  = (String) session.getAttribute("mobile");
//	    String orderid  = (String) session.getAttribute("orderid");
//		
//		model.addAttribute("message",message);
//		model.addAttribute("mobile",mobile);
//		model.addAttribute("orderid",orderid);
//		return new ModelAndView("index", "command", "");
//
//	}	
//	
	@GetMapping(value="/dashboard")
	public String dashboard(Model model) {
		logger.info("opening dashboardPage");
		String token = (String) session.getAttribute("hrms");
		Integer id  = (Integer) session.getAttribute("id");
		if(token!=null) {
			UserDetailsEntity obj = JwtTokenValidator.parseToken(token);
			if(obj!=null) {
				if(obj.getUser_role()==9 || obj.getUser_role()==1 || obj.getUser_role()==2
						|| obj.getUser_role()==3  || obj.getUser_role()==12) {

				model.addAttribute("name",obj.getName());
				model.addAttribute("org",obj.getOrgName());
				model.addAttribute("mobile",obj.getMobile());
				model.addAttribute("email",obj.getEmail());
				model.addAttribute("id",id);
				//return "dashboard";
				return "dashboard";
			}
			 return "error";
		}
		return "redirect:/login";
		
	}
	return "redirect:/login";	
}	

	@GetMapping(value="/profileInfo")
	public ModelAndView ProfileInfo(Model model) {
		String token = (String) session.getAttribute("hrms");
		Integer id  = (Integer) session.getAttribute("id");
		Integer role_id  = (Integer) session.getAttribute("user_role");
		if(token!=null) {
			UserDetailsEntity obj = JwtTokenValidator.parseToken(token);
			if(obj!=null) {
				if(obj.getUser_role()==9 || obj.getUser_role()==1 || obj.getUser_role()==2 
						|| obj.getUser_role()==3 || obj.getUser_role()==10 || obj.getUser_role()==12) {
				model.addAttribute("name",obj.getName());
				model.addAttribute("org",obj.getOrgName());
				model.addAttribute("mobile",obj.getMobile());
				model.addAttribute("email",obj.getEmail());
				model.addAttribute("employerId",id);
				return new ModelAndView("profile-info", "command", "");
			}
			 return new ModelAndView("error", "command", "");
		}
		return new ModelAndView("index", "command", "");
	}
	return new ModelAndView("index", "command", "");
}		

		
}
