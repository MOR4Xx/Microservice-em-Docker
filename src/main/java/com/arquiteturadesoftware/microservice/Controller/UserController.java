package com.arquiteturadesoftware.microservice.Controller;

import com.arquiteturadesoftware.microservice.Model.User;
import com.arquiteturadesoftware.microservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(path = "/buscar")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userService.findAll());
    }
}
