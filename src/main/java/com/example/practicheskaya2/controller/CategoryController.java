package com.example.practicheskaya2.controller;

import com.example.practicheskaya2.dao.CategoryDao;
import com.example.practicheskaya2.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("categories", categoryDao.index());
        return "category/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Category category = categoryDao.show(id);
        model.addAttribute("category", category);
        return "category/show";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryDao.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Категория успешно создана.");
        return "redirect:/category";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Category category = categoryDao.show(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("category") Category updatedCategory, @PathVariable int id, RedirectAttributes redirectAttributes) {
        categoryDao.update(id, updatedCategory);
        redirectAttributes.addFlashAttribute("successMessage", "Категория успешно обновлена.");
        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        categoryDao.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Категория успешно удалена.");
        return "redirect:/category";
    }
}
