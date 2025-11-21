package com.pharmacy.pharmacysystem.controller;

import com.pharmacy.pharmacysystem.entity.Medicine;
import com.pharmacy.pharmacysystem.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable Integer id) {
        return medicineService.getMedicineById(id);
    }

    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Integer id,
                                   @RequestBody Medicine updated) {

        Medicine existing = medicineService.getMedicineById(id);
        if (existing == null) {
            return null;
        }

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setQuantity(updated.getQuantity());
        existing.setExpiry_date(updated.getExpiry_date());

        return medicineService.saveMedicine(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Integer id) {
        medicineService.deleteMedicine(id);
    }
}
