package com.cotodel.crm.web.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncriptResponse {
	String encriptData;
	String encriptKey;
}
