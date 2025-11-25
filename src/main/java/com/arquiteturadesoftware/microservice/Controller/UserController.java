package com.arquiteturadesoftware.microservice.Controller;

import com.arquiteturadesoftware.microservice.Model.User;
import com.arquiteturadesoftware.microservice.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/save")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping(path = "/buscar")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(repository.findAll());
    }
}
