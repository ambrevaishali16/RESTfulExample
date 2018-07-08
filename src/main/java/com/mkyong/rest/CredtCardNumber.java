package com.mkyong.rest;

import java.util.Date;

public class CredtCardNumber {
	private String ccNumber;
	private String ccType;
	private Date expiaryDate;
	private String prefix;
	private int noOfDigits;
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
		// set prefix and number Of digits
		switch(ccType) {
			
			case "MASTERCARD" : this.prefix = "5";
								this.noOfDigits = 16;
								break;
						
			case "VISA" : 		this.prefix = "4";
								this.noOfDigits = 16;
								break;
	
			case "AMEX" : 		this.prefix = "37";
								this.noOfDigits = 15;
								break;
	
			case "Discover" : 	this.prefix = "6";
								this.noOfDigits = 16;
								break;
	
			default : 	break;
	
			}
	}
	public Date getExpiaryDate() {
		return expiaryDate;
	}
	public void setExpiaryDate(Date expiaryDate) {
		this.expiaryDate = expiaryDate;
	}
	public String getPrefix() {
		return prefix;
	}
	public int getNoOfDigits() {
		return noOfDigits;
	}
	
}
