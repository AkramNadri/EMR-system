/** File Name: Appointment.java
 *	Course Name: CST8284 - Object Oriented Programming (Java)
 *	Lab Section: 303
 *	Student Name: Akram Nadri
 *	Date: 11/20/2018
 */
package assign4;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import assign4.Doctor;
import assign4.OurDate;
import assign4.Patient;

public class Appointment implements Serializable {

	private Doctor doctor;
	private Patient patient;
	private OurDate appointmentDate;

	// default constructor with default values
	public Appointment() {
		this(new Patient(), new Doctor(), new OurDate());
	}

	// initial constructor
	public Appointment(Patient patient, Doctor doctor, OurDate appointmentDate) {
		setPatient(patient);
		setDoctor(doctor);
		setAppointmentDate(appointmentDate);
	}

	// getter and setter for doctor and appointments
	public Doctor getDoctor() {
		return doctor;
	}

	// setter for Doctor
	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	// getter for Patient
	public Patient getPatient() {
		return patient;
	}

	// setter for Patients
	private void setPatient(Patient patient) {
		this.patient = patient;
	}

	// getter for appointmentDate
	public OurDate getAppointmentDate() {
		return appointmentDate;
	}

	// setter for appointmentDate
	private void setAppointmentDate(OurDate aDate) {
		Calendar now = new GregorianCalendar();
		Calendar dayInput = new GregorianCalendar();

		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
		dayInput.set(aDate.getYear(), aDate.getMonth() - 1, aDate.getDay());

		boolean errorBadData = false;

		// checks if appointment date is equal to todays date
		if (now.compareTo(dayInput) == 0)
			throw new MedicalClinicException("Appointment cannot be today");

		// checks if appointment date is set in the past
		if (now.compareTo(dayInput) > 0)
			throw new MedicalClinicException("Appointment cannot be in the past");

		this.appointmentDate = aDate;
	}

	// toString for appointment, doctor and patient toString method
	@Override
	public String toString() {
		return "Appointment [appointments=" + appointmentDate.toString() + " " + doctor.toString() + patient.toString();
	}

	// hashCode for Appointment
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	// equals for Appointment
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentDate == null) {
			if (other.appointmentDate != null)
				return false;
		} else if (!appointmentDate.equals(other.appointmentDate))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

}// end of class
