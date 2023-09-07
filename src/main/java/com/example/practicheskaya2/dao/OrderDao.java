package com.example.practicheskaya2.dao;

import com.example.practicheskaya2.models.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao {
    private static int ORDER_COUNT;
    private List<Order> orders;

    {
        orders = new ArrayList<>();
    }

    public List<Order> index() {
        return orders;
    }

    public Order show(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void save(Order newOrder) {
        newOrder.setId(++ORDER_COUNT);
        orders.add(newOrder);
    }

    public void update(int id, Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getId() == id) {
                updatedOrder.setId(id);
                orders.set(i, updatedOrder);
                return;
            }
        }
    }

    public void delete(int id) {
        orders.removeIf(order -> order.getId() == id);
    }
}
