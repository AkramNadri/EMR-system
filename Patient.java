/** File Name: Patient.java
 *	Course Name: Computer Programmer
 *	Lab Section: CST8284 - 303
 *	Student Name: Akram Nadri
 *	Date: 10/28/2018
 */
package assign4;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import assign4.OurDate;
import assign4.Patient;

// class variable declarations
public class Patient implements Serializable {
	private String firstName;
	private String lastName;
	private String healthCardNumber;
	private OurDate birthDate;

	// Default constructor with default values
	public Patient() {
		this("unknown", "unknown", "unknown", new OurDate());
	}

	// initial constructor
	public Patient(String firstName, String lastName, String healthCardNumber, OurDate birthDate) {
		setfirstName(firstName);
		setlastName(lastName);
		setHealthCardNumber(healthCardNumber);
		setbirthDate(birthDate);

	}

	// getter for first name
	public String getfirstName() {
		return firstName;
	}

	// setter for first name
	private void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	// getter for last name
	public String getlastName() {
		return lastName;
	}

	// setter for last name
	private void setlastName(String lastName) {
		this.lastName = lastName;
	}

	// getter for health card number
	public String gethealthCardNumber() {
		return healthCardNumber;
	}

	// setter for health card number
	private void setHealthCardNumber(String healthCardNumber) {
		this.healthCardNumber = healthCardNumber;
	}

	// getter for birthDate
	public OurDate getbirthDate() {
		return birthDate;
	}

	// setter for birthDate
	private void setbirthDate(OurDate birthDate) {
		Calendar now = new GregorianCalendar();
		Calendar dayInput = new GregorianCalendar();

		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
		dayInput.set(birthDate.getYear(), birthDate.getMonth() - 1, birthDate.getDay());

		if (now.compareTo(dayInput) == 0)
			throw new MedicalClinicException("Birthday cannot be today\n");
		if (now.compareTo(dayInput) < 0)
			throw new MedicalClinicException("Birthdate cannot be in the future\n");

		this.birthDate = birthDate;
	}

	// toString method to print out patient values
	@Override
	public String toString() {
		return "patient=Patient [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ " healthCard [healthCardNumber=" + healthCardNumber + "]";
	}

	// hashCode used in equals method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((healthCardNumber == null) ? 0 : healthCardNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	// equals method for Patient class
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (healthCardNumber == null) {
			if (other.healthCardNumber != null)
				return false;
		} else if (!healthCardNumber.equals(other.healthCardNumber))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	

}