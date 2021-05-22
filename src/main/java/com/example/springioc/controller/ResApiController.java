package com.example.springioc.controller;

import com.example.springioc.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }
}
