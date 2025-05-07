package com.example.hacktanton1.controller;

import com.example.hacktanton1.domain.model.Usuario;
import com.example.hacktanton1.domain.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAccountController {
    @Autowired
    UserAccountService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Usuario user) {
        service.save(user);

        return ResponseEntity.ok().build();
    }
}
