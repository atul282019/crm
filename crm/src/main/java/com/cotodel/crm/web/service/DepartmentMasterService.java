package com.cotodel.crm.web.service;

import org.springframework.stereotype.Repository;

import com.cotodel.crm.web.util.EncriptResponse;

@Repository
public interface DepartmentMasterService {
	
	String getDepartmentMaster(String token,EncriptResponse departmentMaster);

}
