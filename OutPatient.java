/** File Name: OutPatient.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.Serializable;

import assign4.OurDate;
import assign4.OutPatient;
import assign4.Patient;

// Subclass for Patient class to test out patient, tests distance from hospital and mobility
public class OutPatient extends Patient implements Serializable{
	private double distanceFromClinic;
	private boolean mobility;

	// default constructor
	public OutPatient() {
		this("unknown", "unknown", "", new OurDate(), -1, false);
	}

	// initial constructor
	public OutPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate,
			double distanceFromClinic, boolean moblitity) {
		super(firstName, lastName, healthCardNumber, birthDate);
		setDistanceFromClinic(distanceFromClinic);
		setMobility(moblitity);
	}

	// get distance for outpatient
	public double getDistanceFromClinic() {
		return distanceFromClinic;
	}

	// set distance of outpatient
	public void setDistanceFromClinic(double distanceFromClinic) {
		this.distanceFromClinic = distanceFromClinic;
	}

	// boolean to check for mobility
	public boolean isMobility() {
		return mobility;
	}

	// set mobility for outpatient
	public void setMobility(boolean mobility) {
		this.mobility = mobility;
	}

	// used for equals method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distanceFromClinic);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (mobility ? 1231 : 1237);
		return result;
	}

	// equals method to test distance and mobility
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutPatient other = (OutPatient) obj;
		if (Double.doubleToLongBits(distanceFromClinic) != Double.doubleToLongBits(other.distanceFromClinic))
			return false;
		if (mobility != other.mobility)
			return false;
		return true;
	}

	/*
	 * display information of out patient with distance from clinic and mobility
	 */
	@Override
	public String toString() {
		return super.toString() + " distanceFromClinic: " + distanceFromClinic + ", mobility: " + mobility;
	}
}

