package com.mkyong.rest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditCardEngine {
	CreditCardNumberGenerator ccNumGen = new CreditCardNumberGenerator();
	/*public static void main(String[] args) {
		System.out.println(new Long("12345678903555"));
		System.out.println(new Long("012345678903555"));
		// Card Type	Prefix	Length
		// MASTERCARD   5		16
		// VISA         4		13/16
		// AMEX         37		16
		// Discover     6		16
		
		
		JavaLuhnAlgorithm test = new JavaLuhnAlgorithm();
		List<CredtCardNumber> cardList = test.generateCCNumber("VISA", 5);
		cardList.addAll(test.generateCCNumber("MASTERCARD", 5));
		cardList.addAll(test.generateCCNumber("AMEX", 5));
		cardList.addAll(test.generateCCNumber("Discover", 5));
		for(CredtCardNumber ccNumber : cardList) {
			System.out.println("card type : " + ccNumber.getCcType() + " CC Numer : " + ccNumber.getCcNumber());	
		}
		
				
	}
	*/
	public List<CredtCardNumber> generateCCNumber (String ccType, int count) {
		List<CredtCardNumber> ccNumberList = new ArrayList<>();
		CredtCardNumber ccNumber = null;
		for(int i=0; i<count; i++) {
			ccNumber = new CredtCardNumber();
			ccNumber.setCcType(ccType);
			ccNumber.setCcNumber(ccNumGen.generate(ccNumber.getPrefix(), ccNumber.getNoOfDigits()));
			if ((ccNumber = validateCreditCardNumber(ccNumber)) != null) {
				ccNumberList.add(ccNumber);				
			} else {
				i--;
			}
		}
		System.out.println(ccNumberList);
		return ccNumberList;
	}
	private  CredtCardNumber validateCreditCardNumber(CredtCardNumber ccNumber) {
		
		String ccStr = ccNumber.getCcNumber();
		boolean isValid = false;
		if ((getNoOfDigits(ccStr) >= 13 && getNoOfDigits(ccStr) <= 16) && 
			(prefixMatched(ccStr, "4") || prefixMatched(ccStr, "5") || 
			prefixMatched(ccStr, "37") || prefixMatched(ccStr, "6"))) {
			isValid = true;
		}
		
		if (isValid) {
			isValid= validateLunhCheck(ccNumber.getCcNumber());
			ccNumber.setExpiaryDate(new Date());
		}
		
		if (!isValid) {
			ccNumber = null;
		}
		return ccNumber;
	}

	private boolean validateLunhCheck(String ccNumber) {
		boolean isValidCC = false;
		int totalSum = getSumOfDigits(ccNumber);
		
		if (totalSum % 10 == 0) {
			System.out.println(ccNumber + " is a valid credit card number");
			isValidCC = true;
		} else {
			System.out.println(ccNumber + " is an invalid credit card number");
		}
		return isValidCC;		
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

	// Return the number of digits in d
    public static int getNoOfDigits(String ccNumber)
    {
        return ccNumber.length();
    }
 
    // Return the first k number of digits from 
    // number. If the number of digits in number
    // is less than k, return number.
    public static String getPrefix(String ccNumber, int startingDigit)
    {
        if (getNoOfDigits(ccNumber) > startingDigit) {
            return ccNumber.substring(0, startingDigit);
        }
        return ccNumber;
    }
    
    public static boolean prefixMatched(String ccNumber, String startDigit)
    {
        return getPrefix(ccNumber, getNoOfDigits(startDigit)).equals(startDigit);
    }
}
