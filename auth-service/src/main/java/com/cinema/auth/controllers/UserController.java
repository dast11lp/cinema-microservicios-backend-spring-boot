package com.cinema.auth.controllers;

import com.cinema.auth.entities.MyUser;
import com.cinema.auth.services.MyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private MyUserService myUserService;

    public UserController (MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        MyUser user = this.myUserService.findById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }
}
