package com.codegnan.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Patients;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000/")
public class PatientController {
	PatientService patientService;
	public PatientController(PatientService patientSerice) {
		super();
		this.patientService = patientSerice;
	}
	public PatientController() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	// Add a new patient
	@PostMapping
	public ResponseEntity<Patients> savePatient(@RequestBody Patients patient){
		Patients savedPatient = patientService.savePatient(patient);
		ResponseEntity<Patients> responseEntity = new ResponseEntity<Patients>(savedPatient, HttpStatus.ACCEPTED);
		return responseEntity;
	}	
}
