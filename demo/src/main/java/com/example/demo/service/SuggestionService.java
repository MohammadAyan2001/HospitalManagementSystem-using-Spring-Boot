package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public SuggestionService(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public String suggestDoctor(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            return "Patient not found";
        }

        Speciality speciality = getSpecialityBySymptom(patient.getSymptom());
        City patientCity;
        try {
            patientCity = City.valueOf(patient.getCity().toUpperCase());
        } catch (IllegalArgumentException e) {
            return "We are still waiting to expand to your location";
        }

        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(patientCity, speciality);
        if (doctors.isEmpty()) {
            return "There isn’t any doctor present at your location for your symptom";
        }

        return doctors.toString(); // Simplified response for illustration
    }

    private Speciality getSpecialityBySymptom(Symptom symptom) {
        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT;
            default:
                throw new IllegalArgumentException("Unknown symptom: " + symptom);
        }
    }
}
