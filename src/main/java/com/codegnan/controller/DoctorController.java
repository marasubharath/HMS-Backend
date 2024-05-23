package com.codegnan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.entity.Doctors;
import com.codegnan.exception.InvalidDoctorIdException;
import com.codegnan.service.DoctorService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:3000/")
public class DoctorController {
	DoctorService doctorService;
	@Autowired
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}
	public DoctorController() {
		super();
		// TODO Auto-generated constructor stub
	}
	// Retrieve a specific doctor
	@GetMapping("/{id}")
	public ResponseEntity<Doctors> getDoctorById(@PathVariable int id) throws InvalidDoctorIdException{
		Doctors doctor = doctorService.findDoctorById(id);
		ResponseEntity<Doctors> responseEntity = new ResponseEntity<>(doctor, HttpStatus.FOUND);
		return responseEntity;
	}
	// Retrieve all Dcotors
	@GetMapping
	public ResponseEntity<List<Doctors>> getAllDoctors(){
		List<Doctors> doctors = doctorService.findAllDoctors();
		ResponseEntity<List<Doctors>> responseEntity = new ResponseEntity<>(doctors, HttpStatus.OK);
		return responseEntity;
	}
	// Add a new Doctor
	@PostMapping
	public ResponseEntity<Doctors> saveDoctor(@RequestBody Doctors doctor){
		Doctors savedDoctor = doctorService.saveDoctor(doctor);
		ResponseEntity<Doctors> responseEntity = new ResponseEntity<Doctors>(savedDoctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	// Edit a specific Doctor details
	@PutMapping("/{id}")
	public ResponseEntity<Doctors> editDoctor(@PathVariable("id") int id, @RequestBody Doctors doctor) throws InvalidDoctorIdException{
		if(id != doctor.getId()) {
			throw new InvalidDoctorIdException("No doctor exists with the id : "+id);
		}
		Doctors editedDoctor = doctorService.editDoctor(doctor);
		ResponseEntity<Doctors> responseEntity = new ResponseEntity<>(editedDoctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	// Delete a specific doctor
	@DeleteMapping("/{id}")
	public ResponseEntity<Doctors> deleteDoctor(@PathVariable int id) throws InvalidDoctorIdException{
		Doctors doctor = doctorService.deleteDoctor(id);
		ResponseEntity<Doctors> responseEntity = new ResponseEntity<>(doctor, HttpStatus.ACCEPTED);
		return responseEntity;
	}
}
