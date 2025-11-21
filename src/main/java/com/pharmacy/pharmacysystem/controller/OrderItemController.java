package com.pharmacy.pharmacysystem.controller;

import com.pharmacy.pharmacysystem.entity.OrderItem;
import com.pharmacy.pharmacysystem.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getAllItems() {
        return orderItemService.getAllItems();
    }

    @PostMapping
    public OrderItem createItem(@RequestBody OrderItem item) {
        return orderItemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        orderItemService.deleteItem(id);
    }
}
