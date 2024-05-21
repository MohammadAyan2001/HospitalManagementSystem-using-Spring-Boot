package com.example.demo.repository;

import com.example.demo.model.City;
import com.example.demo.model.Doctor;
import com.example.demo.model.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findByCityAndSpeciality(City city, Speciality speciality);
}
