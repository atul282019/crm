package com.cotodel.crm.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cotodel.crm.web.properties.ApplicationConstantConfig;
import com.cotodel.crm.web.response.UserForm;
import com.cotodel.crm.web.service.LoginService;
import com.cotodel.crm.web.service.Impl.TokenGenerationImpl;
import com.cotodel.crm.web.util.EncriptResponse;
import com.cotodel.crm.web.util.EncryptionDecriptionUtil;

@Controller
@CrossOrigin
public class OtpSenderController extends CotoDelBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(OtpSenderController.class);
	
	@Autowired
	LoginService loginservice;
	
	@Autowired
	TokenGenerationImpl tokengeneration;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@PostMapping(value="/smsOtpSender")
	public @ResponseBody String sendOtp(HttpServletRequest request,@ModelAttribute("userForm") UserForm userForm,Locale locale,Model model) {
		String profileRes=null;

		try {
			String json = EncryptionDecriptionUtil.convertToJson(userForm);

			EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

			String encriptResponse = loginservice.sendOtp(tokengeneration.getToken(), jsonObject);

   
			EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

			profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profileRes;
	}	
	
	@PostMapping(value="/otpWithOutRegister")
	public @ResponseBody String otpWithOutRegister(HttpServletRequest request,@ModelAttribute("userForm") UserForm userForm,Locale locale,Model model) {
		String profileRes=null;

		try {
			String json = EncryptionDecriptionUtil.convertToJson(userForm);

			EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

			String encriptResponse = loginservice.otpWithOutRegister(tokengeneration.getToken(), jsonObject);

   
			EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

			profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profileRes;
	}	
	
	@PostMapping(value="/smsOtpResender")
	public @ResponseBody String resendOTP(HttpServletRequest request,@ModelAttribute("userForm") UserForm userForm,Locale locale,Model model) {
		String profileRes=null;
		try {
				String json = EncryptionDecriptionUtil.convertToJson(userForm);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = loginservice.resendOtp(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return profileRes;
	}
}
