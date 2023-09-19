package com.example.prct3.controllers;

import com.example.prct3.models.Order;
import com.example.prct3.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping("/order/details/{id}")
    public String showOrderDetails(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "order/order-details";
    }


    @GetMapping("/goToOrders")
    public String goToOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/order-list"; // переход на страницу с заказами
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }
    // Другие методы для добавления, редактирования и удаления заказов

    @GetMapping("/order/add")
    public String showAddOrderForm(Model model) {
        Order order = new Order(); // Создаем новый объект Order для передачи в форму
        model.addAttribute("order", order); // Передаем объект в модель
        return "order/add-order"; // Возвращаем имя шаблона для страницы добавления
    }
    @PostMapping("/order/save")
    public String addOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "order/add-order";
        }

        order.setOrderDate(new Date()); // Устанавливаем текущую дату

        orderRepository.save(order);
        return "redirect:/goToOrders";
    }





    @GetMapping("/order/edit/{id}")
    public String showUpdateOrderForm(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "order/edit-order";
    }

    @PostMapping("/order/edit/{id}")
    public String updateOrder(@PathVariable("id") long id, @ModelAttribute("order") @Valid Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            order.setId(id);
            return "order/edit-order";
        }

        order.setId(id);
        orderRepository.save(order);
        return "redirect:/goToOrders";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        orderRepository.delete(order);
        return "redirect:/goToOrders";
    }

}
