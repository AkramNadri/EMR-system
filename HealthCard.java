/** File Name: HealthCard.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.Serializable;

import assign4.MedicalClinicException;

// class to handle exceptions on health card number
public class HealthCard implements Serializable{

	private String healthCardNumber;

	// default constructor
	public HealthCard() {
		this("unknown");
	}

	// initial constructor
	public HealthCard(String healthCarNumber) {
		setHealthCarNumber(healthCarNumber);
	}

	public String getHealthCarNumber() {
		return healthCardNumber;
	}

	public void setHealthCarNumber(String healthCarNumber) {

		// exception handler to check if health card number is 9 digits and does not
		// contains char
		for (int i = 0; i < healthCarNumber.length(); i++) {
			char studentID = healthCarNumber.charAt(i);
			if (studentID < 48 || studentID > 57) {
				throw new MedicalClinicException("Health Card Number must be 9 digits in length");
			}
		}
		// exception handler to check if health card number is 9 digits
		if (healthCarNumber.length() != 9) {
			throw new MedicalClinicException("Health Card Number must be 9 digits in length");

		}
		this.healthCardNumber = healthCarNumber;
	}

}
