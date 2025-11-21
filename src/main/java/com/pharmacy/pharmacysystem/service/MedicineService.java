package com.pharmacy.pharmacysystem.service;

import com.pharmacy.pharmacysystem.entity.Medicine;
import com.pharmacy.pharmacysystem.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(Integer id) {
        return medicineRepository.findById(id).orElse(null);
    }

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(Integer id) {
        medicineRepository.deleteById(id);
    }
}
