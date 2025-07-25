package com.cotodel.crm.web.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelAddRequest {
	    private Integer id;
	    private String travelSubType;
	    private String mode;
	    private String toBeBookedBy;
	    private String date;
	    private String departure;
	    private String arrival;
	    private String preferredTime;
	    private String timePreference;
	    private String carrierClass;
	    private String carrierDetails;
	    private String paymentMode;
	    private String remarks;
	    private String title;
	    
	    private String hotelDetails;
	    private String location;
	    private String  checkoutDate;
	    private String checkinDate;
	    private String type;
	    
	    private String fromLocation;
	    private String toLocation;
	    
	    private String typeOfMeal;
	    private String startDate;
	    private String numberOfDays;
	    private String arrivalPreference;
	  
	    private String limitAmount;
	    private String amount;
	 
}
