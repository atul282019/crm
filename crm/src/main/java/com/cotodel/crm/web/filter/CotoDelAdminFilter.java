package com.cotodel.crm.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@CrossOrigin(origins = "", allowedHeaders = "")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CotoDelAdminFilter implements Filter, WebMvcConfigurer{

	
	//Logger logger = LoggerFactory.getLogger(CotoDelAdminFilter.class);
	
	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		 
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String requestURI = httpServletRequest.getRequestURI();
		
		boolean isStaticResource1 = httpServletRequest.getRequestURI().contains("cotodel-js/");
		boolean isStaticResource2 = httpServletRequest.getRequestURI().contains("vendor/");
		boolean isStaticResource3 = httpServletRequest.getRequestURI().contains("images/");
		boolean isStaticResourceJs = httpServletRequest.getRequestURI().contains("js/");
		boolean isStaticResourceCss = httpServletRequest.getRequestURI().contains("css/");
		boolean isStaticResourceSass = httpServletRequest.getRequestURI().contains("scss/");
		boolean isStaticResourceImg = httpServletRequest.getRequestURI().contains("img/");
		boolean isStaticResourceFiles = httpServletRequest.getRequestURI().contains("files/");
		boolean isStaticResourcewebfonts = httpServletRequest.getRequestURI().contains("webfonts/");
		boolean isStaticResourcewebfontsblog = httpServletRequest.getRequestURI().contains("/blogs");
		boolean isStaticResourcewebfontssolutions = httpServletRequest.getRequestURI().contains("/solutions");

		HttpSession session = httpServletRequest.getSession(false);
		
		
		HttpServletResponse res = (HttpServletResponse) response;
		//logger.info("inside cors filter method == "+httpServletRequest.getMethod()+" || "+httpServletRequest.getRequestURI());
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
        res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept,"
        		+ " X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers , Authorization,Access-Control-Allow-Origin");

        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
        	res.setStatus(HttpServletResponse.SC_OK);
        	res.setContentType("application/json");
        } 
			

		if (session == null) {
			session = httpServletRequest.getSession(true);
		}
		String login = (String) session.getAttribute("hrms");
		//String login = (String) session.getAttribute("Bis_Login");
		//logger.info("login"+login);
		//logger.info("request uri-------------"+requestURI);
		if(login==null){	
			if( isStaticResourceImg || isStaticResource1 || isStaticResource2 || isStaticResource3 
					|| isStaticResourceJs || isStaticResourceCss || isStaticResourceSass || isStaticResourcewebfonts || isStaticResourceFiles 
					||isStaticResourcewebfontsblog || isStaticResourcewebfontssolutions) {
						chain.doFilter(request, response);
			}
			else if(requestURI.contains("/login")){
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/FleetLogin")){
				RequestDispatcher rd = request.getRequestDispatcher("FleetLogin");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/signup")){
				RequestDispatcher rd = request.getRequestDispatcher("signup");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/signin")){
				RequestDispatcher rd = request.getRequestDispatcher("signin");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/smsOtpSender")){
				RequestDispatcher rd = request.getRequestDispatcher("smsOtpSender");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/userLogin")){
				RequestDispatcher rd = request.getRequestDispatcher("userLogin");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/logout")){
				RequestDispatcher rd = request.getRequestDispatcher("logout");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/tempLogin")){
				RequestDispatcher rd = request.getRequestDispatcher("tempLogin");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/smsOtpResender")){
				RequestDispatcher rd = request.getRequestDispatcher("smsOtpResender");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/userWaitList")){
				RequestDispatcher rd = request.getRequestDispatcher("userWaitList");
				rd.forward(request, response);
			}
		
		
			else if(requestURI.contains("/home")){
				RequestDispatcher rd = request.getRequestDispatcher("home");
				rd.forward(request, response);
			}
		 else if(requestURI.contains("/customlogin")){
				RequestDispatcher rd = request.getRequestDispatcher("customlogin");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/otpWithOutRegister")){
				RequestDispatcher rd = request.getRequestDispatcher("otpWithOutRegister");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/verifyOtpWithOutRegister")){
				RequestDispatcher rd = request.getRequestDispatcher("verifyOtpWithOutRegister");
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("/");
				rd.forward(request, response);
			}
		}else{
			chain.doFilter(request, response);
		} 
	}
	
	public void destroy() {
		
	}
	
}
