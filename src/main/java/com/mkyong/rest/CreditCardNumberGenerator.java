package com.mkyong.rest;
import java.util.Random;

public class CreditCardNumberGenerator {
	private Random random = new Random(System.currentTimeMillis());
	
	 public String generate(String prefix, int ccNumerLength) {

		 // total number of digits to be generated =  total length of the card number - start digits
	     // - minus the check digit at the end.
	        int randomNumberLength = ccNumerLength - (prefix.length() + 1);

	        StringBuilder ccNumber = new StringBuilder(prefix);
	        for (int i = 0; i < randomNumberLength; i++) {
	            int digit = this.random.nextInt(10);
	            ccNumber.append(digit);
	        }

	        // Do the Luhn algorithm to generate the check digit.
	        int checkDigit = this.getCheckDigit(ccNumber.toString());
	        ccNumber.append(checkDigit);

	        return ccNumber.toString();
	    }
	 
	 private int getSumOfDigits(String ccNumber) {		
			int[] ccNumbers = new int[ccNumber.length()];
			
			for (int index = 0; index < ccNumber.length(); index ++) {
				ccNumbers[index] = Integer.parseInt(ccNumber.substring(index, index + 1));
			}
			
			for (int index = ccNumbers.length - 2; index >= 0; index = index - 2) {
				int sum = ccNumbers[index];
				sum = sum * 2;
				if (sum > 9) {
					sum = sum % 10 + sum / 10;
				}
				ccNumbers[index] = sum;
			}
			int totalSum = 0;
			for (int i = 0; i < ccNumbers.length; i++) {
				totalSum += ccNumbers[i];
			}
			return totalSum;
		}
	 
	 private int getCheckDigit(String ccNumber) {
		 int totalSum = getSumOfDigits(ccNumber);
		 int mod = totalSum % 10;
	     return ((mod == 0) ? 0 : 10 - mod); 
	 }
}
