package com.example.prct3.controllers;

import com.example.prct3.models.Laptop;
import com.example.prct3.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LaptopController {

    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/goToLaptop")
    public String goToLaptop(Model model) {
        List<Laptop> laptops = laptopRepository.findAll();
        model.addAttribute("laptops", laptops);
        return "laptop/laptop-list"; // переход на страницу с ноутбуками
    }
    @GetMapping("/addLaptop")
    public String showAddLaptopForm(Model model) {
        Laptop laptop = new Laptop(); // Создаем новый объект Laptop для передачи в форму
        model.addAttribute("laptop", laptop); // Передаем объект в модель
        return "laptop/add-laptop"; // Возвращаем имя шаблона для страницы добавления
    }
    @PostMapping("/laptop/add")
    public String addLaptop(@Valid Laptop laptop, Model model) {
        laptopRepository.save(laptop);
        return "redirect:/goToLaptop";
    }




    @GetMapping("/laptops")
    public String showAllLaptops(Model model) {
        List<Laptop> laptops = laptopRepository.findAll();
        model.addAttribute("laptops", laptops);
        return "laptop-list";
    }

    @GetMapping("/laptop/details/{id}")
    public String showLaptopDetails(@PathVariable("id") long id, Model model) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid laptop Id:" + id));
        model.addAttribute("laptop", laptop);
        return "laptop/laptop-details";
    }

    @GetMapping("/laptop/edit/{id}")
    public String showUpdateLaptopForm(@PathVariable("id") long id, Model model) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid laptop Id:" + id));
        model.addAttribute("laptop", laptop);
        return "laptop/update-laptop";
    }

    @PostMapping("/laptop/update/{id}")
    public String updateLaptop(@PathVariable("id") long id, @Valid Laptop laptop, Model model) {
        laptop.setId(id);
        laptopRepository.save(laptop);
        return "redirect:/goToLaptop";
    }

    @GetMapping("/laptop/delete/{id}")
    public String deleteLaptop(@PathVariable("id") long id, Model model) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid laptop Id:" + id));
        laptopRepository.delete(laptop);
        return "redirect:/goToLaptop";
    }
}
