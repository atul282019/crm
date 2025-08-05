package com.cotodel.crm.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cotodel.crm.web.function.common.CommonUtils;
import com.cotodel.crm.web.properties.ApplicationConstantConfig;
import com.cotodel.crm.web.service.TicketSupportService;
import com.cotodel.crm.web.util.CommonUtility;
import com.cotodel.crm.web.util.EncriptResponse;
import com.cotodel.crm.web.util.MessageConstant;

@Service
public class TicketSupportServiceImpl implements TicketSupportService{
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
//	@Override
//	public String submitTicketDetails(String token, EncriptResponse jsonObject) {
//		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.submitTicket);
//	}
//	@Override
//	public String getAllTicket(String token, EncriptResponse jsonObject) {
//		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.getTicket);
//	}
	@Override
	public String getTicketListForAction(String token, EncriptResponse jsonObject) {
		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.getAdminTicketForAction);
	}
//	@Override
//	public String getTicketDetailById(String token, EncriptResponse jsonObject) {
//		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.getTicketDetailById);
//	}
//	@Override
//	public String replyTicket(String token, EncriptResponse jsonObject) {
//		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.submitTicketReply);
//
//	}
//	@Override
//	public String ticketReplyHistory(String token, EncriptResponse jsonObject) {
//		return CommonUtility.userRequest(token,MessageConstant.gson.toJson(jsonObject), applicationConstantConfig.employerServiceBaseUrl+CommonUtils.ticketReplyHistory);
//	}

}
