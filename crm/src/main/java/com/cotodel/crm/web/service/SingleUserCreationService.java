package com.cotodel.crm.web.service;

import org.springframework.stereotype.Repository;

import com.cotodel.crm.web.response.UserRegistrationRequest;
//import com.cotodel.crm.web.response.UserRequest;
//import com.cotodel.crm.web.response.UserWaitList;
import com.cotodel.crm.web.util.EncriptResponse;

@Repository
public interface SingleUserCreationService {

	String singleUserCreationEncript(String token, EncriptResponse userForm);
	String singleUserCreation(String token, EncriptResponse userForm);

	String getUser(String token, EncriptResponse userForm);

	String userWaitList(String token, EncriptResponse userWaitList);
	String getuserWaitList(String token, EncriptResponse userWaitList);
	String reputeRequestSave(String token, EncriptResponse jsonObject);
	String updateuserWaitList(String token, EncriptResponse userWaitList);
	String saveReputeUserDetailEncript(String token, EncriptResponse jsonObject);
	String userDetailByMobileNo(String token, EncriptResponse jsonObjectIdToken2);
	
}
