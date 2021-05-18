/** File Name: MedicalClinicException.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.Serializable;

// class to handle exceptions and extends RuntimeExceptions
public class MedicalClinicException extends RuntimeException implements Serializable {

	// default constructor
	public MedicalClinicException() {
		super();
	}

	// overloaded constructor
	public MedicalClinicException(String message) {
		super(message);
	}

}
