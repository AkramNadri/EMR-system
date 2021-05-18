/** File Name: MedicalClinicUserInterface.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;

import java.awt.Menu;
import java.io.Serializable;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

// User interface class for inputs 
public class MedicalClinicUserInterface {

	// private class variable declarations
	private static final int ADD_PATIENT = 1;
	private static final int ADD_APPOINTMENT = 2;
	private final int CANCEL_APPOINTMENTS = 3;
	private static final int LIST_APPOINTMENTS = 4;
	private static final int WRITE_PATIENT = 5;
	private static final int LOAD_PATIENT = 6;
	private static final int QUIT = 7;
	private MedicalClinic clinic;
	Scanner input = new Scanner(System.in);
	private String hCardNumber;

	// default constructor
	public MedicalClinicUserInterface() {

		// clinic object
		clinic = new MedicalClinic();
		menu();
	}

	// ****** Main method - compiler starts here ******
	public static void main(String[] args) {
		new MedicalClinicUserInterface();
	}

	// menu constructor with options using switch/case statement
	public void menu() {
		int selection = 0;
		while (selection != QUIT) {

			// options menu 1 through 5
			System.out.println("Please make a choice: ");
			System.out.println("1. Enter a new patient");
			System.out.println("2. Make an appointment for patient");
			System.out.println("3. cancel appointment");
			System.out.println("4. List all appointments");
			System.out.println("5. Write patient data to file");
			System.out.println("6. Load patient data");
			System.out.println("7. Quit\n");

			selection = input.nextInt();

			switch (selection) {

			// Invoke methods inside menu() method
			// option 1 to add patient
			case ADD_PATIENT:
				addPatient();
				break;

			// option 2 to add appointment
			case ADD_APPOINTMENT:
				addAppointment();
				break;

			// option 3 to cancel an appointment
			case CANCEL_APPOINTMENTS:
				cancelAppointments();
				break;

			// option 4 to list appointments
			case LIST_APPOINTMENTS:
				listAppointments();
				break;

			// option 5 to write patients out
			case WRITE_PATIENT:
				writePatientsOut();
				break;

			// option 6 to read patients in
			case LOAD_PATIENT:
				readPatientsIn();
				break;

			// option 7 quits the selection menu
			case QUIT:
				System.out.println("Goodbye");
				break;

			// option 8 prints list of doctors
			case 8:
				printDoctors();
				break;

			// option 9 prints list of patients
			case 9:
				printPatients();
				break;

			// default in switch/case statement for handling exception if incorrect choice
			// is made
			default:
				System.out.println("Please select options 1 - 7\n");
			}

		} // end while loop
	} // end menu() method

	// method to check for maximum capacity and invoked in Appointment, Patient and
	// Doctor methods
	private static boolean checkCapacity(int currentSize, int maxSize) {
		return currentSize < maxSize;

	}

	public void writePatientsOut() {
		clinic.filePatientsOut();
	}

	public void readPatientsIn() {
		clinic.filePatientsIn();
	}

	// method for adding patient to ArrayList Patients
	public void addPatient() {

		// User inputs patients information
		System.out.println("Enter first name: ");
		String firstName = (input.next());

		System.out.println("Enter last name: ");
		String lastName = (input.next());

		String hCardNumber;

		// do while loop to check validateCard
		do {
			System.out.println("Enter health card number: ");
			hCardNumber = input.next();
		} while (!cardnumberValidate(hCardNumber));

		Boolean checkBirthday = false;
		// Date of birth is taken as String and converted to Integer using parseInt.
		// Values being stored into Day,Month,Year.
		do {
			String stringSet = " ";
			do {
				System.out.println("Enter birth date DDMMYYYY: ");
				stringSet = input.next();
			} while (!dateValidate(stringSet));

			int day = (Integer.parseInt(stringSet.substring(0, 2)));
			int month = (Integer.parseInt(stringSet.substring(2, 4)));
			int year = (Integer.parseInt(stringSet.substring(4, 8)));
			OurDate birthDate = new OurDate(day, month, year);

			// user selects patient type
			System.out.println("Enter type of patient: ");
			System.out.println("1. Maternity Patient");
			System.out.println("2. OutPatient");
			System.out.println("3. Regular Patient");

			int type = input.nextInt();

			// try catch for Maternity and OutPatient input fields
			try {
				switch (type) {

				// input fields for MaternityPatient
				case 1:
					OurDate dueDate;

					// duedate input for maternity patient
					System.out.print("due date");
					String dueDateString = input.next();

					dueDate = new OurDate(dueDateString);

					// nutrition test for maternity patient
					System.out.print("nutrition testing yes/no?");
					String nutrition = input.next();
					boolean nut = nutrition.trim().equalsIgnoreCase("yes");
					clinic.addPatient(firstName, lastName, hCardNumber, birthDate, dueDate, nut);
					break;

				// input fields for OutPatient
				case 2:

					// distance set for out patient
					System.out.print("distance from hospital");
					String distanceString = input.next();
					double dis = Double.parseDouble(distanceString);

					// mobility set for out patient
					System.out.print("mobility yes/no");
					String moblie = input.next();
					boolean mob = moblie.trim().equalsIgnoreCase("yes");
					clinic.addPatient(firstName, lastName, hCardNumber, birthDate, dis, mob);
					break;
				case 3:
					clinic.addPatient(firstName, lastName, hCardNumber, birthDate);
					break;
				default:
					// default case if
					System.out.println("Please select options 1 to 3.\n");
					break;

				}
				checkBirthday = true;

			} catch (MedicalClinicException e) {
				System.out.println(e.getMessage());
				checkBirthday = false;
			}
		} while (!checkBirthday);
	}

	// add appointment method which create an appointment date for patient and
	// doctor
	public void addAppointment() {
		Patient existingP = null;
		String hCardNumber1;

		// loops through array to check for existing patient health card number
		do {
			System.out.println("Enter health card number: ");
			hCardNumber1 = input.next();
		} while (!cardnumberValidate(hCardNumber1));

		// System.out.print("Enter health card number: ");
		// int hCardNumber = input.nextInt();

		ArrayList<Patient> patients = clinic.getPatients();
		for (int i = 0; i < patients.size(); i++) {
			// check if patient exists using value of HealthCardNumber
			if (clinic.getPatients().get(i).gethealthCardNumber().equals(hCardNumber1)) {
				System.out.println(patients.get(i).toString());
				existingP = patients.get(i);
				break;
			}
		}

		ArrayList<Doctor> doctors = clinic.getDoctors();
		int x = 1;
		for (Doctor theDoctor : doctors) {
			System.out.println(x++ + " " + theDoctor.getLastName() + " " + theDoctor.getFirstName() + " "
					+ theDoctor.getSpecialty());
		}
		int selectedDoctor;
		OurDate setDate;

		// user input values to select doctor and appointment date
		System.out.println("Enter number for doctor selection:");
		selectedDoctor = input.nextInt();

		// place in do while
		String stringSet1 = " ";
		boolean valid = false;

		Doctor dR = doctors.get(selectedDoctor - 1);

		// if statement to check if appointments array has reached max of 10
		if (clinic.getAppointments().size() < clinic.getMaxAppointments()) {

			do {
				// try catch statement for appointment date in addAppointment method
				try {
					System.out.println("Enter desired appointment date DDMMYYYY:");
					setDate = clinic.getDateFromString(input.next());

					clinic.addAppointment(existingP, dR, setDate);
					valid = true;
					// System.out.println(clinic.getAppointments().size());

				} catch (MedicalClinicException e) {
					System.out.println(e.getMessage());
				}

			} while (!valid);

		} else {

			// error message output if list appointment is full or taken
			System.err.println("the Appointment list is full");

		}
	} // end addAppointment method

	// alreadyTaken method checks arraylist for duplicate appointment dates for
	// doctors
	private boolean dateTaken(int x, OurDate y) {
		if (clinic.getAppointments().size() == 0) {
			return true;
		}

		// nested if statements to check for existing appointments on appointment dates
		else {
			for (Appointment appointment : clinic.getAppointments()) {
				if (appointment != null)
					if (appointment.getDoctor() == clinic.getDoctors().get(x - 1)) {
						if (appointment.getAppointmentDate().equals(y)) {
							System.err.println("Doctor already has appointment that day");
							return false;
						}
					}
			}
			return true;
		}
	} // end dateTaken method

	// cancelAppointments method removes a specific appointment in the specified
	// index
	public void cancelAppointments() {
		ArrayList<Appointment> apps = clinic.getAppointments();
		String cardNumber;

		// do while statement to loop health card number input for cancelAppointment
		// method
		do {
			System.out.println("Enter health card number: ");
			cardNumber = input.next();
		} while (!cardnumberValidate(cardNumber));

		Patient patient = null;
		for (Patient p : clinic.getPatients()) {

			// search through list for duplicate health card number
			if (p.gethealthCardNumber().equals(cardNumber)) {
				System.out.println(p);
				patient = p;
			}
		}
		int i = 1;
		for (Doctor doctor : clinic.getDoctors()) {
			System.out.print(i++ + " " + doctor);
		}

		// user input to select doctor
		System.out.println("Enter number for doctor selection:");
		int selection = input.nextInt();

		Doctor doctor = clinic.getDoctors().get(selection - 1);

		// user input to set appointment date
		System.out.println("Enter Appointment Date DDMMYYY");
		String date = input.next();

		OurDate appointmentDate = new OurDate(date);

		int indexPosition = -1;
		// System.out.println(apps.size());
		for (Appointment a : apps) {
			if (new Appointment(patient, doctor, appointmentDate).equals(a)) {
				indexPosition = apps.indexOf(a);
			}
		}

		// confirmation for cancelled appointment
		System.out.println("Appointment Cancelled \n");

		if (indexPosition >= 0)
			apps.remove(indexPosition);
		else
			// error if user input for appointment does not exist
			System.err.println("Can not find the appointment");

	}

	// listAppointment method to list current existing appointments
	public void listAppointments() {

		for (Appointment appointmentList : clinic.getAppointments()) {
			System.out.println(appointmentList.toString());
		}

		// statement prints out if no appointments exist
		if (clinic.getAppointments().size() == 0) {
			System.out.println("No appointments\n");
		}
	}

	// cardnumberValidates method to handle exception for health card numbers length
	// < or > then 9
	private boolean cardnumberValidate(String hCardNumber) {
		try {
			HealthCard hCard = new HealthCard(hCardNumber);
		} catch (MedicalClinicException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// dateValidate to set OurDate format
	private boolean dateValidate(String st) {
		try {
			OurDate date = new OurDate(st.substring(0, 2), st.substring(2, 4), st.substring(4, 8));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// printDoctors method to print list of doctors
	public void printDoctors() {
		clinic.getDoctors().forEach(System.out::println);
	}

	// printPatients method to print current existing patients
	public void printPatients() {
		for (Patient p : clinic.getPatients())
			System.out.println(p);
	}
}