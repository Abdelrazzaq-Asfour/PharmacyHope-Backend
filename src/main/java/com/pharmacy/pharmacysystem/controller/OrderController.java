package com.pharmacy.pharmacysystem.controller;

import com.pharmacy.pharmacysystem.entity.Order;
import com.pharmacy.pharmacysystem.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        return orderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id,
                             @RequestBody Order updated) {

        Order existing = orderService.getOrderById(id);
        if (existing == null) {
            return null;
        }

        existing.setUser(updated.getUser());
        existing.setOrder_date(updated.getOrder_date());
        existing.setStatus(updated.getStatus());
        existing.setTotal_amount(updated.getTotal_amount());

        return orderService.saveOrder(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}
