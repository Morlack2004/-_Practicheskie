package com.example.practicheskaya2.controller;

import com.example.practicheskaya2.dao.OrderDao;
import com.example.practicheskaya2.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderDao orderDao;

    @Autowired
    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", orderDao.index());
        return "order/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Order order = orderDao.show(id);
        model.addAttribute("order", order);
        return "order/show";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Order());
        return "order/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order") Order order) {
        orderDao.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Order order = orderDao.show(id);
        model.addAttribute("order", order);
        return "order/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("order") Order updatedOrder, @PathVariable int id) {
        orderDao.update(id, updatedOrder);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        orderDao.delete(id);
        return "redirect:/orders";
    }
}
