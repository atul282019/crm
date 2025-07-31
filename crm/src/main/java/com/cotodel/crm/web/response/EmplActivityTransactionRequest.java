package com.cotodel.crm.web.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmplActivityTransactionRequest {
	
	private Long orgId;	
	private String employerCode;	
	private String activityStatusCode;	
	private String assignedToMob;	
	private String remarks;	
	private String createdby;
	private String response;
	
	private String orgname;	
	private int leadtypeid;	
	private String leadtypedesc;	
	private String assignedName;	
	private String contactpersonName;
	private String contactpersonMobile;
	private String deptName;
	private LocalDateTime assgnmentSentDate;

}
