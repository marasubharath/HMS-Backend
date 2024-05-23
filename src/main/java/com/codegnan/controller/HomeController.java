package com.codegnan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codegnan.entity.Doctors;
import com.codegnan.entity.Patients;
import com.codegnan.exception.InvalidDoctorIdException;
import com.codegnan.exception.InvalidPatientIdException;
import com.codegnan.service.DoctorService;
import com.codegnan.service.PatientService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3000/")
public class HomeController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    // Constructor injection for dependencies
    public HomeController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    // Retrieve all doctors
    @GetMapping
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> doctors = doctorService.findAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Retrieve all patients
    @GetMapping("/patients")
    public ResponseEntity<List<Patients>> getAllPatients() {
        List<Patients> patients = patientService.findAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Retrieve patient by ID
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable int id) throws InvalidPatientIdException {
        Patients patient = patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Edit a patient's details
    @PutMapping("/patients/{id}")
    public ResponseEntity<Patients> editPatient(@PathVariable("id") int id, @RequestBody Patients patient) throws InvalidPatientIdException {
        if (id != patient.getId()) {
            throw new InvalidPatientIdException("No patient exists with the id: " + id);
        }
        Patients editedPatient = patientService.editPatient(patient);
        return new ResponseEntity<>(editedPatient, HttpStatus.ACCEPTED);
    }

    // Delete a patient by ID
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Patients> deletePatient(@PathVariable int id) throws InvalidPatientIdException {
        Patients patient = patientService.deletePatient(id);
        return new ResponseEntity<>(patient, HttpStatus.ACCEPTED);
    }

    // Retrieve all patients associated with a specific doctor
    @GetMapping("/doctors/{id}/patients")
    public ResponseEntity<List<Patients>> getPatientsByDoctor(@PathVariable int id) throws InvalidDoctorIdException {
        Doctors doctor = doctorService.findDoctorById(id);
        List<Patients> patients = patientService.findAllPatientsByDoctor(doctor);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
