package com.cotodel.crm.web.service;

import org.springframework.stereotype.Repository;

//import com.cotodel.crm.web.response.CompanyProfileDetail;
import com.cotodel.crm.web.response.EmployeeProfileRequest;
import com.cotodel.crm.web.util.EncriptResponse;

@Repository
public interface CompanyService {

//	String saveCompany(String token, EncriptResponse employeeProfileRequest);

	String getEmployerList(String token, EncriptResponse employeeProfileRequest);
	//String getorgsubType(String token, EncriptResponse employeeProfileRequest);
	//String getpayrollDetails(String token, EncriptResponse employeeProfileRequest);
//
//	String getGSTDetailsByGSTNumber(String token, EncriptResponse employeeProfileRequest);
//
//	String saveOrganizationDetail(String token, EncriptResponse companyProfileDetail);
//	
//	String updateOrganizationDetail(String token, EncriptResponse companyProfileDetail);
//
//	String activeInactiveVoucherAmount(String token, EncriptResponse jsonObject);
//
//	String usedAmountByCategories(String token, EncriptResponse jsonObject);
//
//	String loadActiveInactiveUserList(String token, EncriptResponse jsonObject);
//
//	String erupiVoucherCreateListLimit(String token, EncriptResponse jsonObject);
//
//	String getVoucherTransactionList(String token, EncriptResponse jsonObject);

}
