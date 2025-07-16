package com.cotodel.crm.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


public class CommonUtility {
	
	private static final Logger logger = LogManager.getLogger(CommonUtility.class);
	
	
	public static String userRequest(String sAccessToken,String requestJson,String url){
		String returnStr=null;
		String companyId = "HRMS00001";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try{
			logger.info(" Request Json for url"+url+"---"+requestJson);
			System.out.println(" Request Json for url"+url+"---"+requestJson);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("companyId", companyId);
			
			if(sAccessToken!=null && !sAccessToken.isEmpty()) {
				headers.setBearerAuth(sAccessToken);
			}
			
			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
			logger.info(" response Json---"+returnStr);
			return returnStr;
		}catch(HttpStatusCodeException e) {
			logger.error("HttpStatusCodeException error in---"+url+"-"+e.getResponseBodyAsString());
			return e.getResponseBodyAsString();
		}catch(Exception e){
			logger.error(" error in---"+url+"-"+e);
			return null;
		}finally {
			restTemplate=null;headers=null;sAccessToken=null;requestJson=null;url=null;	
		}		
	}
	
	
	public static String bulkUserRequest(String sAccessToken,String requestJson,String url,String publicPath,String privatePath){
		String returnStr=null;
		String decript=null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try{
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			if(sAccessToken!=null && !sAccessToken.isEmpty()) {
				headers.setBearerAuth(sAccessToken);
			}
			EncriptResponse jsonEncriptObject=EncryptionDecriptionUtil.encriptResponse(requestJson, publicPath);
			String jsonEncript =  EncryptionDecriptionUtil.convertToJson(jsonEncriptObject);
			logger.info(" Request Json for url"+url+"---"+jsonEncript);
			 
			HttpEntity<String> entity = new HttpEntity<String>(jsonEncript,headers);
 
			returnStr = restTemplate.postForObject(url, entity, String.class);
			EncriptResponse enResponse= EncryptionDecriptionUtil.convertFromJson(returnStr, EncriptResponse.class);
			decript=EncryptionDecriptionUtil.decriptResponse(enResponse.getEncriptData(), enResponse.getEncriptKey(), privatePath);
			logger.info(" response Json---"+decript);
			return decript;
		}catch(HttpStatusCodeException e) {
			logger.error("HttpStatusCodeException error in---"+url+"-"+e.getResponseBodyAsString());
			return e.getResponseBodyAsString();
		}catch(Exception e){
			logger.error(" error in---"+url+"-"+e);
			return null;
		}finally {
			restTemplate=null;headers=null;sAccessToken=null;requestJson=null;url=null;	
		}		
	}

	public static String getTokenRequest(String sAccessToken,String requestJson,String companyId,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try{
			logger.info(" Request URL---"+url);
			logger.info(" Request header---"+companyId);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("companyId", companyId);
			if(sAccessToken!=null && !sAccessToken.isEmpty()) {
				headers.setBearerAuth(sAccessToken);
			}

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	        returnStr = response.getBody();
			logger.info(" response Json---"+returnStr);
			return returnStr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			restTemplate=null;headers=null;	
		}		
	}

	

	public static String getuserRequest(String sAccessToken,String requestJson,String url){
		String returnStr=null;
		String companyId = "HRMS00001";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try{
			logger.info(" Request URL---"+url);
			//logger.info(" Request header---"+companyId);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("companyId", companyId);
			if(sAccessToken!=null && !sAccessToken.isEmpty()) {
				headers.setBearerAuth(sAccessToken);
			}

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	        returnStr = response.getBody();
			logger.info(" response Json---"+returnStr);
			return returnStr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			restTemplate=null;headers=null;	
		}		
	}
	
	public static String expenseRequest(String sAccessToken,String requestJson,String url,String publicPath,String privatePath){
		String returnStr=null;
		String decript=null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try{
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			if(sAccessToken!=null && !sAccessToken.isEmpty()) {
				headers.setBearerAuth(sAccessToken);
			}
			EncriptResponse jsonEncriptObject=EncryptionDecriptionUtil.encriptResponse(requestJson, publicPath);
			String jsonEncript =  EncryptionDecriptionUtil.convertToJson(jsonEncriptObject);
			logger.info(" Request Json for url"+url+"---"+jsonEncript);
			 
			HttpEntity<String> entity = new HttpEntity<String>(jsonEncript,headers);
 
			returnStr = restTemplate.postForObject(url, entity, String.class);
			EncriptResponse enResponse= EncryptionDecriptionUtil.convertFromJson(returnStr, EncriptResponse.class);
			decript=EncryptionDecriptionUtil.decriptResponse(enResponse.getEncriptData(), enResponse.getEncriptKey(), privatePath);
			logger.info(" response Json---"+decript);
			return decript;
		}catch(HttpStatusCodeException e) {
			logger.error("HttpStatusCodeException error in---"+url+"-"+e.getResponseBodyAsString());
			return e.getResponseBodyAsString();
		}catch(Exception e){
			logger.error(" error in---"+url+"-"+e);
			return null;
		}finally {
			restTemplate=null;headers=null;sAccessToken=null;requestJson=null;url=null;	
		}		
	}


	public static String getAccessToken(String code,String redirectUri,String sendurl) {
		try {
            // URL for the token request
            //URL url = new URL("https://app.demohrms.stg.repute.net/oauth2/token");
			URL url = new URL(sendurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set HTTP request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Basic YTc2Y2M2OGMtZmI5MS00ODI2LTk5NDctYmJlYmVlZjAxZTBhOnZlVEh1Y1UwOGJ1M1YzTnhFaE9MOUFkeDJFUVZWYlBT");

            // Enable input and output streams for POST data
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Form data to send in the POST request
            String urlParameters = "code="+code
                    + "&grant_type=authorization_code"
                    + "&redirect_uri="+redirectUri;

            // Write the data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = urlParameters.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());
                return response.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
