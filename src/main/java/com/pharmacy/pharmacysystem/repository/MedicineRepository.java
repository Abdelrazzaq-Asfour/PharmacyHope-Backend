package com.pharmacy.pharmacysystem.repository;

import com.pharmacy.pharmacysystem.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
