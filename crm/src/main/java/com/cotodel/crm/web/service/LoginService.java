package com.cotodel.crm.web.service;

import com.cotodel.crm.web.response.UserRegistrationRequest;
import com.cotodel.crm.web.util.EncriptResponse;
import com.cotodel.crm.web.util.MessageConstant;

public interface LoginService {

	String sendOtp(String token, EncriptResponse userForm);
	String verifyOtp(String token, EncriptResponse userForm);
	public void sendEmailToEmployee(UserRegistrationRequest userForm);
	public void sendEmailVerificationCompletion(UserRegistrationRequest userForm);
	String getToken(String companyId);
	String resendOtp(String token, EncriptResponse userForm);
	String verifyVoucherIssueOTP(String token, EncriptResponse userForm);
	String getReputeToken(String token, EncriptResponse jsonObjectRepute);
	String otpWithOutRegister(String token, EncriptResponse jsonObject);
	String verifyOtpWithOutRegister(String token, EncriptResponse jsonObject);

}
