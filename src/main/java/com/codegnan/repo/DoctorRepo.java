package com.codegnan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codegnan.entity.Doctors;

public interface DoctorRepo extends JpaRepository<Doctors, Integer> {

}
