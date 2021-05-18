/** File Name: MaternityPatient.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import assign4.MaternityPatient;
import assign4.OurDate;
import assign4.Patient;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

// subclass for Patient class to test maternity patients, test nutrition and due date
public class MaternityPatient extends Patient implements Serializable {
	private OurDate dueDate;
	private boolean nutritionTesting;

	// default constructor
	public MaternityPatient() {
		this("unknown", "unknown", "", new OurDate(), estimateDueDate(), false);
	}

	// initial constructor for maternity patient
	public MaternityPatient(String firstName, String lastName, String healthCardNumber, OurDate birthDate,
			OurDate dueDate, boolean nutritionTesting) {
		super(firstName, lastName, healthCardNumber, birthDate);
		if (dueDate == null)
			dueDate = estimateDueDate();
		setDueDate(dueDate);
		setNutritionTesting(nutritionTesting);
	}

	private static OurDate estimateDueDate() {
		Calendar dueDate = Calendar.getInstance();
		dueDate.add(Calendar.MONTH, 9);
		Date due = dueDate.getTime();
		return new OurDate(String.valueOf(due.getDay()), String.valueOf(due.getMonth()), String.valueOf(due.getYear()));
	}

	// get due date for maternity patient
	public OurDate getDueDate() {
		return dueDate;
	}

	// set due date for Maternity patient
	private void setDueDate(OurDate dueDate) {
		this.dueDate = dueDate;
	}

	// get information if patient needs nutrition testing
	public boolean isNutritionTesting() {
		return nutritionTesting;
	}

	// boolean to check if patient requires nutrition testing
	private void setNutritionTesting(boolean nutritionTesting) {
		this.nutritionTesting = nutritionTesting;
	}

	// toString method to display duedate, nutrition testing for MaternityPatient
	@Override
	public String toString() {
		return super.toString() + " dueDate: " + dueDate + ", nutritionTesting: " + nutritionTesting;
	}

	// hashCode used for equals method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + (nutritionTesting ? 1231 : 1237);
		return result;
	}

	// equals method for MaternityPatient class
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaternityPatient other = (MaternityPatient) obj;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (nutritionTesting != other.nutritionTesting)
			return false;
		return true;
	}

}
