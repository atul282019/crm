package com.cotodel.crm.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErupiVoucherCreateTransactionRequest {

	private Long orgId;
	private String timePeriod;
	String mobile;
	
}
