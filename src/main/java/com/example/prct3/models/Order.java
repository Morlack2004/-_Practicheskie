package com.example.prct3.models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal; // Добавлен импорт для работы с денежными значениями
import java.util.Date; // Добавлен импорт для работы с датами

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    private BigDecimal totalPrice; // Добавлено поле для общей стоимости

    @Temporal(TemporalType.DATE) // Указываем, что храним только дату
    private Date orderDate; // Добавлено поле для даты заказа

    // Другие поля заказа

    public Order() {}

    // Геттеры и сеттеры для полей

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
