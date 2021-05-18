/** File Name: MedicalClinic.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicalClinic implements Serializable {

	// maximum size for patients, appointments and doctors
	private static final int MAXPATIENTS = 5;
	private static final int MAXAPPOINTMENTS = 5;
	private static final int MAXDOCTORS = 5;

	// variables for appointments, patients and doctors
	private static int numberAppointment;
	private static int numberPatients;
	private static int numberDoctors;

	// private ArrayList for appointments, patients and doctors
	private ArrayList<Appointment> appointments;
	private ArrayList<Patient> patients;
	private ArrayList<Doctor> doctors;

	// class variables
	int choice;
	Patient existingP;

	// scanner for user input
	Scanner input = new Scanner(System.in);

	// MedicalClinic default constructor
	public MedicalClinic() {
		patients = new ArrayList<>();
		appointments = new ArrayList<>();
		doctors = new ArrayList<>();

		// list of doctor values in Doctors ArrayList
		doctors.add(new Doctor("Rakesh ", "Singh", "Nephrologist"));
		doctors.add(new Doctor("Pardis ", "Honarvar", "Family"));
		doctors.add(new Doctor("James ", "Bond", "Dermatologist"));
		doctors.add(new Doctor("Francois ", "DaSilva", "Oncologist"));
		doctors.add(new Doctor("Billy ", "Bob", "Cardiologist"));

	} // end of default constructor

	// Option 2 to add appointment
	public void addAppointment(Patient patient, Doctor doctor, OurDate appointmentDate) throws MedicalClinicException {
		Appointment appointment = new Appointment(patient, doctor, appointmentDate);

		appointments.add(appointment);
		MedicalClinic.numberAppointment = appointments.size();
	}

	public void filePatientsOut() {

		try {
			FileOutputStream fos = new FileOutputStream("PatientData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fos);

			out.writeObject(patients);
			out.flush();

			out.close();
			fos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("File loaded.");
	}

	public void filePatientsIn() {

		try {
			FileInputStream fis = new FileInputStream("PatientData.ser");

			ObjectInputStream in = new ObjectInputStream(fis);

			ArrayList<Patient> patientFile = (ArrayList<Patient>) in.readObject();

			this.patients = patientFile;
			System.out.println(patientFile);

		} catch (IOException ex) {
			System.out.println("File does not exist");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		System.out.println("File Read.");
	}

	// option 3 to cancel appointment
	public void cancelAppointment(int index) {
		appointments.remove(index - 1); // remove given index
	}

	// getter numberAppointments
	public int getNumberAppointments() {
		return MedicalClinic.numberAppointment;
	}

	// getter numberPatients
	public int getNumberPatients() {
		return MedicalClinic.numberPatients;
	}

	// getter numberDoctors
	public int getNumberDoctors() {
		return MedicalClinic.numberDoctors;
	}

	// getter MAXAPPOINTMENTS
	public int getMaxAppointments() {
		return MedicalClinic.MAXAPPOINTMENTS;
	}

	// getter MAXPATIENTS
	public int getMaxPatients() {
		return MedicalClinic.MAXPATIENTS;
	}

	// getter MAXDOCTORS
	public int getMaxDoctors() {
		return MedicalClinic.MAXDOCTORS;
	}

	// Option 4 to display existing appointments
	public void listAppointments() {

		for (Appointment dmx : appointments) {
			if (dmx != null)
				System.out.println(dmx);
		}
	}

	// retrieves day, month and year from OurDate
	public OurDate getDateFromString(String s) {

		// substring for day, month, year
		OurDate date = new OurDate(Integer.parseInt(s.substring(0, 2)), Integer.parseInt(s.substring(2, 4)),
				Integer.parseInt(s.substring(4, 8)));

		return date;
	}

	// public ArrayList for Patients
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	// public ArrayList for Appointments
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	// public ArrayList for Doctors
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	// add outpatient to ArrayList
	public void addPatient(String firstName, String lastName, String parseInt, OurDate birthDate, double dis,
			boolean nut) {
		patients.add(new OutPatient(firstName, lastName, parseInt, birthDate, dis, nut));
	}

	// add Maternity Patient to ArrayList
	public void addPatient(String firstName, String lastName, String parseInt, OurDate birthDate, OurDate dueDate,
			boolean nut) {
		patients.add(new MaternityPatient(firstName, lastName, parseInt, birthDate, dueDate, nut));
	}

	// add Patient to ArrayList
	public void addPatient(String firstName, String lastName, String parseInt, OurDate birthDate) {
		patients.add(new Patient(firstName, lastName, parseInt, birthDate));
	}

} // end of class
