package com.pharmacy.pharmacysystem.service;

import com.pharmacy.pharmacysystem.entity.Medicine;
import com.pharmacy.pharmacysystem.entity.OrderItem;
import com.pharmacy.pharmacysystem.repository.MedicineRepository;
import com.pharmacy.pharmacysystem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MedicineRepository medicineRepository;

    public OrderItemService(OrderItemRepository orderItemRepository,
                            MedicineRepository medicineRepository) {
        this.orderItemRepository = orderItemRepository;
        this.medicineRepository = medicineRepository;
    }

    public List<OrderItem> getAllItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getItemById(Integer id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem saveItem(OrderItem item) {
        Medicine med = medicineRepository.findById(item.getMedicine().getId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        if (med.getQuantity() < item.getQuantity()) {
            throw new RuntimeException("Not enough quantity in stock");
        }

        med.setQuantity(med.getQuantity() - item.getQuantity());
        medicineRepository.save(med);

        BigDecimal unitPrice = med.getPrice();
        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

        item.setUnit_price(unitPrice);
        item.setSubtotal(subtotal);

        return orderItemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        Medicine med = item.getMedicine();
        med.setQuantity(med.getQuantity() + item.getQuantity());
        medicineRepository.save(med);

        orderItemRepository.delete(item);
    }
}
