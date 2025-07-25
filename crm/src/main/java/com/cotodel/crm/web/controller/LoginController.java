package com.cotodel.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cotodel.crm.web.jwt.util.JwtTokenGenerator;
import com.cotodel.crm.web.properties.ApplicationConstantConfig;
import com.cotodel.crm.web.response.UserDetailsEntity;
import com.cotodel.crm.web.response.UserForm;
import com.cotodel.crm.web.service.LoginService;
import com.cotodel.crm.web.service.Impl.TokenGenerationImpl;
import com.cotodel.crm.web.util.EncriptResponse;
import com.cotodel.crm.web.util.EncryptionDecriptionUtil;
import com.cotodel.crm.web.util.MessageConstant;

@Controller
@CrossOrigin
public class LoginController extends CotoDelBaseController{

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	LoginService loginservice;
	
	@Autowired
	TokenGenerationImpl tokengeneration;
	
	@PostMapping(value="/userLogin")
	public String validateLogin(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("userForm") UserForm userForm, BindingResult result, HttpSession session, Model model,RedirectAttributes redirect) {
		String profileRes=null;JSONObject profileJsonRes=null;String screenName="index";
		String profileResRepute=null; 
		Integer orgid=null;
		JSONObject profileResJsonRepute=null;
		String message =null; String otpmobile =null; String orderid=null;
		UserDetailsEntity obj =null;
		 Date currentDate = new Date();
	        
	        // Define the desired date format
	        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss E d MMM yyyy");
	        // Format the date
	        String formattedDate = formatter.format(currentDate);
	        
	        // Print the formatted date
	        System.out.println(formattedDate);
		try {
			String otp = null;
			  
			otp= userForm.getPassword1()+userForm.getPassword2()+userForm.getPassword3()+userForm.getPassword4()+userForm.getPassword5()+userForm.getPassword6();      
			
			   userForm.setOtp(otp);
				String json = EncryptionDecriptionUtil.convertToJson(userForm);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = loginservice.verifyOtp(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			
			logger.info(profileRes);
			
			if(!ObjectUtils.isEmpty(profileRes)) {
				
				profileJsonRes= new JSONObject(profileRes);
				
				if(profileJsonRes.getBoolean("status") && profileJsonRes.getString("message").equalsIgnoreCase(MessageConstant.RESPONSE_SUCCESS)) {
				
					//set token in session
					request.getSession(true).setAttribute("email", profileJsonRes.getJSONObject("data").getString("email"));									  
					request.getSession(true).setAttribute("hrms", profileJsonRes.getJSONObject("data").getString("mobile"));
					request.getSession(true).setAttribute("organizationName", profileJsonRes.getJSONObject("data").getString("organizationName"));
					request.getSession(true).setAttribute("username", profileJsonRes.getJSONObject("data").getString("username"));
					request.getSession(true).setAttribute("user_role",  profileJsonRes.getJSONObject("data").getInt("role_id"));
					request.getSession(true).setAttribute("formattedDate",  formattedDate);
					if(profileJsonRes.getJSONObject("data").getInt("role_id") == 1) {
			
					request.getSession(true).setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
					session.setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
					model.addAttribute("id",profileJsonRes.getJSONObject("data").getInt("employerid"));
					
					session.setAttribute("empId", profileJsonRes.getJSONObject("data").getInt("id"));
					model.addAttribute("empId",profileJsonRes.getJSONObject("data").getInt("id"));
				    orgid = profileJsonRes.getJSONObject("data").getInt("employerid");
					}
					else if(profileJsonRes.getJSONObject("data").getInt("role_id") == 9) {
	
						request.getSession(true).setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						session.setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						model.addAttribute("id",profileJsonRes.getJSONObject("data").getInt("employerid"));
						
						session.setAttribute("empId", profileJsonRes.getJSONObject("data").getInt("id"));
						model.addAttribute("empId",profileJsonRes.getJSONObject("data").getInt("id"));
					    orgid = profileJsonRes.getJSONObject("data").getInt("employerid");
						
					}
					else if(profileJsonRes.getJSONObject("data").getInt("role_id") == 3) {
						request.getSession(true).setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						session.setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						model.addAttribute("id",profileJsonRes.getJSONObject("data").getInt("employerid"));
						
						session.setAttribute("empId", profileJsonRes.getJSONObject("data").getInt("id"));
						model.addAttribute("empId",profileJsonRes.getJSONObject("data").getInt("id"));
					    orgid = profileJsonRes.getJSONObject("data").getInt("employerid");
					}// id and empid is same in 1,3,9 
					else {
						request.getSession(true).setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						session.setAttribute("id", profileJsonRes.getJSONObject("data").getInt("employerid"));
						model.addAttribute("id",profileJsonRes.getJSONObject("data").getInt("employerid"));
						
						session.setAttribute("empId", profileJsonRes.getJSONObject("data").getInt("id"));
						model.addAttribute("empId",profileJsonRes.getJSONObject("data").getInt("id"));
					    orgid = profileJsonRes.getJSONObject("data").getInt("employerid");
					}
					session.setAttribute("email", profileJsonRes.getJSONObject("data").getString("email"));
					session.setAttribute("mobile", profileJsonRes.getJSONObject("data").getString("mobile"));
					session.setAttribute("username", profileJsonRes.getJSONObject("data").getString("username"));
					session.setAttribute("organizationName", profileJsonRes.getJSONObject("data").getString("organizationName"));
					//check user is in database or not
					//userRes=loginService.checkUserExsistance(userForm);

					//logger.info("user login user entity "+userRes);

					//check user is active
					//check role is defined
					// check user is verified or not
					
						Map<String,Object> userRole = new HashMap<String,Object>();
						userRole.put("email", profileJsonRes.getJSONObject("data").getString("email"));
						userRole.put("mobile", profileJsonRes.getJSONObject("data").getString("mobile"));
						String email = profileJsonRes.getJSONObject("data").getString("email");
						String mobile = profileJsonRes.getJSONObject("data").getString("mobile");
						String username = profileJsonRes.getJSONObject("data").getString("username");
						Integer user_role = profileJsonRes.getJSONObject("data").getInt("role_id");
						
						String token	=	JwtTokenGenerator.generateToken(email,mobile,username,user_role,orgid, MessageConstant.SECRET);
						//return JSONUtil.setJSONResonse(MessageConstant.RESPONSE_SUCCESS, MessageConstant.TRUE, userRole,token);
					    request.getSession(true).setAttribute("hrms", token);
					    // switch case to identify the user screen login
					    
					switch (String.valueOf(profileJsonRes.getJSONObject("data").getInt("role_id"))) {	
					case "0":
						screenName="index";
						model.addAttribute("message", "No Role assigned to User. Please contact to Organisation Admin !!");
						break;	
					case "1":
						screenName="dashboard";
						
						break;	
					case "9":
						screenName="dashboard";
						break;	
					}
					return screenName;
				}else if(profileJsonRes.getString("message").equalsIgnoreCase("Invalid Request")){
					    session.setAttribute("message", "Incorrect OTP ||");
					    session.setAttribute("mobile", userForm.getMobile());
					    session.setAttribute("orderid", userForm.getOrderId());
						model.addAttribute("message",(String)session.getAttribute("message"));
						model.addAttribute("mobile",(String) session.getAttribute("mobile"));
						model.addAttribute("orderid",(String) session.getAttribute("orderid"));
						return "index";
				}
				else if(profileJsonRes.getString("message").equalsIgnoreCase("Expired")){
					  model.addAttribute("message", "OTP Expired");
					 return "index";
				}
				else if(profileJsonRes.getString("message").equalsIgnoreCase("Link expired")){
					  model.addAttribute("message", "OTP Expired");
					 return "index";
				}
			}else {
				model.addAttribute("message", "Incorrect OTP ||");
			}	
			return "index";
		}catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("message", "System not responding, Please try again later..!");
			return "index";
		}finally {
			profileRes=null;profileJsonRes=null;screenName=null; obj =null;
		}
	}

}