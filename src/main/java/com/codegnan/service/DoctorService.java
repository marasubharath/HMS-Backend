package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.entity.Doctors;
import com.codegnan.exception.InvalidDoctorIdException;
import com.codegnan.repo.DoctorRepo;

@Service
public class DoctorService {
	DoctorRepo doctorRepo;
	public DoctorService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorService(DoctorRepo doctorRepo) {
		super();
		this.doctorRepo = doctorRepo;
	}
	@Autowired
	public void setDoctorRepo(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}
	public Doctors saveDoctor(Doctors doctor) {
		return doctorRepo.save(doctor);
	}
	public List<Doctors> findAllDoctors(){
		return doctorRepo.findAll();
	}
	public Doctors findDoctorById(int id) throws InvalidDoctorIdException{
		Optional<Doctors> optDoctor = doctorRepo.findById(id);
		if(!(optDoctor.isPresent())) {
			throw new InvalidDoctorIdException("No doctor exits with the id : "+id);
		}
		return optDoctor.get();
	}
	public Doctors editDoctor(Doctors doctor) throws InvalidDoctorIdException {
		Doctors foundDoctor = findDoctorById(doctor.getId());
		return doctorRepo.save(foundDoctor);
	}
	public Doctors deleteDoctor(int id) throws InvalidDoctorIdException{
		Doctors doctor = findDoctorById(id);
		doctorRepo.delete(doctor);
		return doctor;
	}
}
