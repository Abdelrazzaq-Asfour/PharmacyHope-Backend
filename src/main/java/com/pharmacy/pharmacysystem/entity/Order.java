package com.pharmacy.pharmacysystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime order_date;

    private String status;

    private BigDecimal total_amount;

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getOrder_date() { return order_date; }
    public void setOrder_date(LocalDateTime order_date) { this.order_date = order_date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotal_amount() { return total_amount; }
    public void setTotal_amount(BigDecimal total_amount) { this.total_amount = total_amount; }
}
