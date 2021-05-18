/** File Name: Doctor.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.Serializable;

import assign4.Doctor;

// doctor class
public class Doctor implements Serializable {

	private String firstName;
	private String lastName;
	private String specialty;

	// default constructor for doctors
	public Doctor() {
		this("unknown", "unknown", "specialty");
	}

	// initial constructor for Doctor values
	public Doctor(String firstName, String lastName, String specialty) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
	}

	// getter for firstName of Doctor
	public String getFirstName() {
		return firstName;
	}

	// setter for firstName of Doctor
	private void setFirstName(String FirstName) {
		this.firstName = FirstName;
	}

	// getter for lastName of Doctor
	public String getLastName() {
		return lastName;
	}

	// setter for lastName of Doctor
	private void setLastName(String LastName) {
		this.lastName = LastName;
	}

	// getter and setter for specialty value for Doctor
	public String getSpecialty() {
		return specialty;
	}

	private void setSpecialty(String Specialty) {
		this.specialty = Specialty;
	}

	// toString method output Doctor values
	@Override
	public String toString() {
		return "doctor=" + firstName + lastName + "," + " " + specialty + "\n";
	}

	// hashCode for Doctor
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		return result;
	}

	// equals for Doctors
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
			return false;
		return true;
	}

}

