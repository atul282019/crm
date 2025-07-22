package com.cotodel.crm.web.function.common;

public interface CommonUtils {


	public static String tokenUrl="/tokenService/Api/get/access-token";
	public static String sendOtp = "/userServices/Api/getOtp";
	public static String otpWithOutRegister = "/userServices/Api/get/otpWithOutRegister";
	public static String sendOtpNew = "/userServices/Api/getOtpNew";
	public static String resendOtpNew = "/userServices/Api/getOtpResend";
	public static String verifyOtpNew = "/userServices/Api/verifyOtpNew";
	public static String verifyOtpWithOutRegister = "/userServices/Api/get/verifyOtpWithOutRegister";
	public static String verifyOtp = "/userServices/Api/verifyOtp";

	public static String verifyVoucherIssueOTP ="/userServices/Api/verifyOtpWithoutUser";
	public static String  saveReputeIdTokenData = "/userServices/Api/add/saveRepute";
	public static String registerReputeUserDetail = "/userServices/Api/add/saveReputeDetails";
	public static String getReputeToken = "/userServices/Api/get/reputeToken";
	
	//register user url 
	public static String registerUserUrl = "/userServices/Api/add/saveEmployerOnBoardingDetails";
}
