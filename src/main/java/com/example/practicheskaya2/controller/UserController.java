package com.example.practicheskaya2.controller;

import com.example.practicheskaya2.dao.UserDao;
import com.example.practicheskaya2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String index() {
        return "index"; // Имя шаблона страницы index.html
    }

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userDao.index());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        User user = userDao.show(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDao.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        User user = userDao.show(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User updatedUser, @PathVariable int id) {
        userDao.update(id, updatedUser);
        return "redirect:/user";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userDao.delete(id);
        return "redirect:/user";
    }
}
