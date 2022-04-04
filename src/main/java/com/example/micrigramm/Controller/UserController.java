package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.UserDTO;
import com.example.micrigramm.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public UserDTO registerUser(@RequestParam String name, String login, String email, String password) throws Exception {
        return userService.register(name, login, email, password);

    }

    @GetMapping("/searchUser/{userSearch}")
    public UserDTO getByUser(@PathVariable String userSearch) {
        return userService.findUser(userSearch);
    }
}
