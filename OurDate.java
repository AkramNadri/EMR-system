/** File Name: OurDate.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.util.Calendar;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Class to create date for date  
 * of birth and appointment dates
 */
public class OurDate implements Serializable {

	// OurDate class variables
	private static final Calendar CALENDAR = Calendar.getInstance();
	private int day;
	private int month;
	private int year;

	private boolean isValid;

	// default constructor for OurDate
	public OurDate() {
	}

	// OurDate method with format to return substring in specified indexes
	public OurDate(String st) {
		this(st.substring(0, 2), st.substring(2, 4), st.substring(4, 8));
	}

	// constructor for OurDate with day, month and year passed in parameters
	public OurDate(String day, String month, String year) {
		this(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
	}

	// initial constructor for OurDate
	public OurDate(int day, int month, int year) {

		setYear(year);

		setMonth(month);

		setDay(day);
	}

	// getters and setters for OurDate
	public int getDay() {
		return day;
	}

	// exception handling boundaries for day
	private void setDay(int day) {
		if (day > 31 || day < 0)
			throw new MedicalClinicException("day cannot be more then 31 less then 0");

		this.day = day;
	}

	// getter for month
	public int getMonth() {
		return month;
	}

	// setter for month with exception handling for min & max boundaries
	private void setMonth(int month) {
		if (month < 1 || month > 12)
			throw new MedicalClinicException("Month should be from 1 - 12");

		this.month = month;
	}

	// getter for year
	public int getYear() {
		return year;
	}

	// setter for year with exception handling min & max years
	private void setYear(int year) {
		if (year < 1900)
			throw new MedicalClinicException("Year cannot be before 1900");
		this.year = year;
	}

	// for boolean isValid
	public boolean isValid() {
		return isValid;
	}

	// setter for Valid method
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	// toString method for day, month and year print out
	@Override
	public String toString() {
		return day + "/" + month + "/" + year + ",";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + (isValid ? 1231 : 1237);
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OurDate other = (OurDate) obj;
		if (day != other.day)
			return false;
		if (isValid != other.isValid)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
