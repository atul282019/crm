package com.cotodel.crm.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cotodel.crm.web.properties.ApplicationConstantConfig;
import com.cotodel.crm.web.response.EmplActivityTransactionRequest;
//import com.cotodel.crm.web.response.ActiveUserListClass;
import com.cotodel.crm.web.response.EmployeeProfileRequest;
import com.cotodel.crm.web.response.EmployerDetailsRequest;
import com.cotodel.crm.web.response.ErupiVoucherCreateTransactionRequest;
//import com.cotodel.crm.web.response.ErupiVoucherAmountRequest;
//import com.cotodel.crm.web.response.ErupiVoucherCreateTransactionRequest;
//import com.cotodel.crm.web.response.ErupiVoucherPurposeCodeRequest;
import com.cotodel.crm.web.service.CompanyService;
import com.cotodel.crm.web.service.Impl.TokenGenerationImpl;
import com.cotodel.crm.web.util.EncriptResponse;
import com.cotodel.crm.web.util.EncryptionDecriptionUtil;
import com.cotodel.crm.web.response.ErupiTicketSaveRequest;
import com.cotodel.crm.web.service.TicketSupportService;

@Controller
@CrossOrigin
public class DashboardController extends CotoDelBaseController{


	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	public TicketSupportService ticketSupportService;

	@Autowired
	TokenGenerationImpl tokengeneration;
	
	private static final String SECRET_KEY = "0123456789012345"; // Must match frontend
    private static final String CLIENT_KEY = "client-secret-key"; // Extra validation
	
	@GetMapping(value="/getEmployerList")
	public @ResponseBody String getEmployerList(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmployeeProfileRequest employeeProfileRequest) {
			logger.info("getCompanyProfileStatus");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.getEmployerList(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	
	@GetMapping(value="/getstatusMaster")
	public @ResponseBody String getstatusMaster(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmployeeProfileRequest employeeProfileRequest) {
			logger.info("getCompanyProfileStatus");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.getstatusMaster(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	@GetMapping(value="/getActivityTransactionlist")
	public @ResponseBody String getActivityTransactionlist(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmplActivityTransactionRequest emplActivityTransactionRequest) {
			logger.info("getCompanyProfileStatus");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(emplActivityTransactionRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.getActivityTransactionlist(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	@PostMapping(value="/addemplActivityTransaction")
	public @ResponseBody String addemplActivityTransaction(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmplActivityTransactionRequest emplActivityTransactionRequest) {
			logger.info("activeInactiveVoucherAmount");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(emplActivityTransactionRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.addemplActivityTransaction(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	@PostMapping(value="/updateEmployerDetailsByCrm")
	public @ResponseBody String updateEmployerDetailsByCrm(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmployerDetailsRequest employerDetailsRequest) {
			logger.info("activeInactiveVoucherAmount");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(employerDetailsRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.updateEmployerDetailsByCrm(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	
	@PostMapping(value="/getEmployerDetails")
	public @ResponseBody String getEmployerDetails(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,EmployerDetailsRequest employerDetailsRequest) {
			logger.info("activeInactiveVoucherAmount");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(employerDetailsRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.getEmployerDetails(tokengeneration.getToken(), jsonObject);

	   
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
	@GetMapping(value = "/getTicketListForAction")
	public @ResponseBody String getTicketListForAction(HttpServletRequest request, ModelMap model, Locale locale,
			HttpSession session, ErupiTicketSaveRequest erupiTicketSaveRequest) {
		String profileRes = null;
		
		try {
			String json = EncryptionDecriptionUtil.convertToJson(erupiTicketSaveRequest);

			EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

			String encriptResponse =  ticketSupportService.getTicketListForAction(tokengeneration.getToken(), jsonObject);
  
			EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

			profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    	return profileRes;
	}
//	@PostMapping(value="/activeInactiveVoucherAmount")
//	public @ResponseBody String activeInactiveVoucherAmount(HttpServletRequest request, ModelMap model,Locale locale,
//			HttpSession session,ErupiVoucherAmountRequest employeeProfileRequest) {
//			logger.info("activeInactiveVoucherAmount");	
//			String token = (String) session.getAttribute("crm");
//			String profileRes=null;
//		
//			try {
//				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);
//
//				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);
//
//				String encriptResponse = companyService.activeInactiveVoucherAmount(tokengeneration.getToken(), jsonObject);
//
//	   
//				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);
//
//				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	   
//		return profileRes;
//	}
//	
//	@PostMapping(value="/usedAmountByCategories")
//	public @ResponseBody String usedAmountByCategories(HttpServletRequest request, ModelMap model,Locale locale,
//			HttpSession session,ErupiVoucherPurposeCodeRequest employeeProfileRequest) {
//			logger.info("usedAmountByCategories");	
//			String token = (String) session.getAttribute("crm");
//			String profileRes=null;
//		
//			try {
//				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);
//
//				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);
//
//				String encriptResponse = companyService.usedAmountByCategories(tokengeneration.getToken(), jsonObject);
//
//	   
//				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);
//
//				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	   
//		return profileRes;
//	}
//	
//	@PostMapping(value="/loadActiveInactiveUserList")
//	public @ResponseBody String loadActiveInactiveUserList(HttpServletRequest request, ModelMap model,Locale locale,
//			HttpSession session,ActiveUserListClass employeeProfileRequest) {
//			logger.info("usedAmountByCategories");	
//			String token = (String) session.getAttribute("crm");
//			String profileRes=null;
//		
//			try {
//				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);
//
//				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);
//
//				String encriptResponse = companyService.loadActiveInactiveUserList(tokengeneration.getToken(), jsonObject);
//
//	   
//				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);
//
//				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	   
//		return profileRes;
//	}
//	
//	@PostMapping(value="/erupiVoucherCreateListLimit")
//	public @ResponseBody String erupiVoucherCreateListLimit(HttpServletRequest request, ModelMap model,Locale locale,
//			HttpSession session,ErupiVoucherPurposeCodeRequest employeeProfileRequest) {
//			logger.info("erupiVoucherCreateListLimit");	
//			String token = (String) session.getAttribute("crm");
//			String profileRes=null;
//		
//			try {
//				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);
//
//				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);
//
//				String encriptResponse = companyService.erupiVoucherCreateListLimit(tokengeneration.getToken(), jsonObject);
//				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);
//
//				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	   
//		return profileRes;
//	}
//	
	@PostMapping(value="/getVoucherTransactionList")
	public @ResponseBody String getVoucherTransactionList(HttpServletRequest request, ModelMap model,Locale locale,
			HttpSession session,ErupiVoucherCreateTransactionRequest employeeProfileRequest) {
			logger.info("ErupiVoucherCreateTransactionRequest");	
			String token = (String) session.getAttribute("crm");
			String profileRes=null;
		
			try {
				String json = EncryptionDecriptionUtil.convertToJson(employeeProfileRequest);

				EncriptResponse jsonObject=EncryptionDecriptionUtil.encriptResponse(json, applicationConstantConfig.apiSignaturePublicPath);

				String encriptResponse = companyService.getVoucherTransactionList(tokengeneration.getToken(), jsonObject);
				EncriptResponse userReqEnc =EncryptionDecriptionUtil.convertFromJson(encriptResponse, EncriptResponse.class);

				profileRes =  EncryptionDecriptionUtil.decriptResponse(userReqEnc.getEncriptData(), userReqEnc.getEncriptKey(), applicationConstantConfig.apiSignaturePrivatePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		return profileRes;
	}
//	
//	
}
