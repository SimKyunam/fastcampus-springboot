package com.example.server.controller;

import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = User.builder()
                .name(name)
                .age(age)
                .build();

        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ){
        log.info("userId: {}, userName: {}", userId, userName);
        log.info("authorization: {}, custom: {}", authorization, customHeader);
        log.info("cline req : {}", user);
        return user;
    }
}
