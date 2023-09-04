package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/calculator")
    public String calculatorForm() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculateResult(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {
        double result = 0.0;

        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Деление на ноль невозможно");
                    return "calculator";
                }
                break;
            default:
                model.addAttribute("error", "Неизвестная операция");
                return "calculator";
        }

        model.addAttribute("result", result);
        return "result";
    }
}