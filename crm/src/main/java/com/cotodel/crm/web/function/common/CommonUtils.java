package com.cotodel.crm.web.function.common;

public interface CommonUtils {


	public static String tokenUrl="/tokenService/Api/get/access-token";
	public static String sendOtp = "/userServices/Api/getOtp";
	public static String otpWithOutRegister = "/userServices/Api/get/otpWithOutRegister";
	//public static String sendOtpNew = "/userServices/Api/getOtpNew";
	public static String sendOtpNew = "/userServices/Api/crm/getOtp";
	public static String resendOtpNew = "/userServices/Api/getOtpResend";
	//public static String verifyOtpNew = "/userServices/Api/verifyOtpNew";
	public static String verifyOtpNew = "/userServices/Api/crm/verifyOtp";
	public static String verifyOtpWithOutRegister = "/userServices/Api/get/verifyOtpWithOutRegister";
	public static String verifyOtp = "/userServices/Api/verifyOtp";

	public static String verifyVoucherIssueOTP ="/userServices/Api/verifyOtpWithoutUser";
	public static String  saveReputeIdTokenData = "/userServices/Api/add/saveRepute";
	public static String registerReputeUserDetail = "/userServices/Api/add/saveReputeDetails";
	public static String getReputeToken = "/userServices/Api/get/reputeToken";
	
	//register user url 
	public static String registerUserUrl = "/userServices/Api/add/saveEmployerOnBoardingDetails";
	//get company details
	public static String getEmployerList = "/userServices/Api/get/getEmployerList";
	//for showing status in right side modal on dashboard
	public static String getstatusMaster = "/erupicrm/Api/get/statusMaster";
	
	public static String updateEmployerDetailsByCrm = "/userServices/Api/update/updateEmployerDetailsByCrm";
	
	public static String addemplActivityTransaction = "/erupicrm/Api/add/emplActivityTransaction";
	
	public static String getActivityTransactionlist = "/erupicrm/Api/get/emplActivityTransList";
	//for getting employer details 
	public static String getEmployerDetails = "/userServices/Api/get/getEmployerDetails";
	
	public static String  getVoucherTransactionList = "/empService/Api/get/erupiVoucherCreateListRedeem";
	
	public static String getAdminTicketForAction = "/empService/Api/get/allTicketAdmin";

	public static String getEmployeeOnboarding ="/empService/Api/get/empOnboardingList";
	
	public static String getErupiLinkAccountDetail  ="/empService/Api/get/erupiLinkAccountList";
	
	public static String sendOtp2FactorWithTemplateId = "/userServices/Api/get/sendOtp";
	
	public static String sendWhatsApp ="/userServices/Api/get/sendWhatsApp";
	
	public static String  activateEmployer = "/userServices/Api/update/userActive";
	
	
}
