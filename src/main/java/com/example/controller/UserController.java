package com.example.controller;

import com.example.repository.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="")
    public @ResponseBody String addUser (@RequestBody User user) {
        userRepository.save(user);
        return "User created";
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteUser (@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getUser (@PathVariable int id) {
        return userRepository.findById(id).get();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}