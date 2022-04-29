package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.UserDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5501", maxAge = 36000)
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user) throws Exception {
        userService.register(user);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity<Void> login( Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            userService.loadUserByUsername(user.getUsername());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchUser/{userSearch}")
    public UserDTO getByUser(@PathVariable String userSearch) {
        return userService.findUser(userSearch);
    }
}
