package ru.geekbrains.SpringHW7.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SpringHW7.model.User;
import ru.geekbrains.SpringHW7.services.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class UserController {
    UserService userService;

    // Переход на публичную страницу по умолчанию
    @GetMapping("/")
    public String defaults() {
        return "redirect:/public-data";
    }

    // Запрос публичной страницы
    @GetMapping("/public-data")
    public String getPublicData(Model model) {
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("time", LocalTime.now());
        return "public-data";

    }

    // Запрос приватной страницы для управления пользователями
    @GetMapping("/private-data")
    public String getPrivateData(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "private-data";
    }

    // Переход на форму добавления пользователя
    @GetMapping("/user-create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    // Добавление нового пользователя
    @PostMapping("/user-create")
    public String createUser(User user, Model model) {
        userService.addUser(user);
        model.addAttribute("users", userService.getUsers());
        log.info("Пользователь " + user + " успешно добавлен");
        return "redirect:/private-data";
    }

    // Открытие окна изменения пользователя
    @GetMapping("/update/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    // Обновление данных пользователя
    @PostMapping("/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        log.info("Пользователь с id = " + user.getId() + " изменен");
        return "redirect:/private-data";
    }


    // Удаление нового пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        log.info("Пользователь с id = " + id + " удален");
        return "redirect:/private-data";
    }

}
