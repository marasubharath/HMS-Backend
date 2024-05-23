package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.repo.PatientRepo;
import com.codegnan.entity.Doctors;
import com.codegnan.entity.Patients;
import com.codegnan.exception.InvalidPatientIdException;
@Service
public class PatientService {
	PatientRepo patientRepo;
	
	public PatientService(PatientRepo patinetRepo) {
		super();
		this.patientRepo = patinetRepo;
	}
	public PatientService() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public void setPatinetRepo(PatientRepo patinetRepo) {
		this.patientRepo = patinetRepo;
	}
	public Patients savePatient(Patients patient) {
		return patientRepo.save(patient);
	}
	public Patients findPatientById(int id) throws InvalidPatientIdException{
		Optional<Patients> optPatient = patientRepo.findById(id);
		if(!(optPatient.isPresent())) {
			throw new InvalidPatientIdException("Patient with id : "+id+" not exist");
		}
		return optPatient.get();
	}
	public List<Patients> findAllPatients(){
		return patientRepo.findAll();
	}
	public Patients editPatient(Patients patient) throws InvalidPatientIdException {
		findPatientById(patient.getId());
		return patientRepo.save(patient);
	}
	public List<Patients> findAllPatientsByDoctor(Doctors doctor){
		return patientRepo.findAllByDoctor(doctor);
	}
	public Patients deletePatient(int id) throws InvalidPatientIdException {
		Patients patient = findPatientById(id);
		patientRepo.delete(patient);
		return patient;
	}
	
}
