package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.UserDTO;
import com.example.micrigramm.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userSearch}")
    public UserDTO getByUser(@PathVariable String userSearch){
            return userService.findUser(userSearch);
    }
}
