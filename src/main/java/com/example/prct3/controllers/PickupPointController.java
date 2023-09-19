package com.example.prct3.controllers;

import com.example.prct3.models.PickupPoint;
import com.example.prct3.repositories.PickupPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PickupPointController {

    private final PickupPointRepository pickupPointRepository;

    @Autowired
    public PickupPointController(PickupPointRepository pickupPointRepository) {
        this.pickupPointRepository = pickupPointRepository;
    }

    @GetMapping("/goToPoint")
    public String goToPoint(Model model) {
        List<PickupPoint> pickupPoints = pickupPointRepository.findAll();
        model.addAttribute("pickupPoints", pickupPoints); // Исправлено на "pickupPoints"
        return "pickuppoint/pickup-point-list"; // переход на страницу с ноутбуками
    }


    @GetMapping("/pickup-points")
    public String showAllPickupPoints(Model model) {
        List<PickupPoint> pickupPoints = pickupPointRepository.findAll();
        model.addAttribute("pickupPoints", pickupPoints);
        return "pickup-point-list";
    }
    @GetMapping("/pickup-point/add")
    public String showAddPickupPointForm(Model model) {
        PickupPoint pickupPoint = new PickupPoint();
        model.addAttribute("pickupPoint", pickupPoint);
        return "pickuppoint/add-pickup-point";
    }

    @PostMapping("/pickup-point/add")
    public String addPickupPoint(@ModelAttribute("pickupPoint") @Valid PickupPoint pickupPoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pickuppoint/add-pickup-point";
        }

        pickupPointRepository.save(pickupPoint);
        return "redirect:/goToPoint";
    }

    @GetMapping("/pickup-point/edit/{id}")
    public String showUpdatePickupPointForm(@PathVariable("id") long id, Model model) {
        PickupPoint pickupPoint = pickupPointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pickup point Id:" + id));
        model.addAttribute("pickupPoint", pickupPoint);
        return "pickuppoint/edit-pickup-point";
    }

    @PostMapping("/pickup-point/edit/{id}")
    public String updatePickupPoint(@PathVariable("id") long id, @ModelAttribute("pickupPoint") @Valid PickupPoint pickupPoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pickupPoint.setId(id);
            return "pickuppoint/edit-pickup-point";
        }

        pickupPoint.setId(id);
        pickupPointRepository.save(pickupPoint);
        return "redirect:/goToPoint";
    }

    @GetMapping("/pickup-point/delete/{id}")
    public String deletePickupPoint(@PathVariable("id") long id, Model model) {
        PickupPoint pickupPoint = pickupPointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pickup point Id:" + id));
        pickupPointRepository.delete(pickupPoint);
        return "redirect:/goToPoint";
    }

    @GetMapping("/pickup-point/details/{id}")
    public String showPickupPointDetails(@PathVariable("id") long id, Model model) {
        PickupPoint pickupPoint = pickupPointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pickup point Id:" + id));
        model.addAttribute("pickupPoint", pickupPoint);
        return "pickuppoint/pickup-point-details";
    }
}
