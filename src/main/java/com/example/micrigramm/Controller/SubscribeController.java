package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.SubscribeDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.SubscribeService;
import com.example.micrigramm.Service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5501", maxAge = 36000)
@RestController
@RequestMapping("/subscribes")
public class SubscribeController {
    private final UserService userService;
    private final SubscribeService subscribeService;

    public SubscribeController(UserService userService, SubscribeService subscribeService) {
        this.userService = userService;
        this.subscribeService = subscribeService;
    }

    @PostMapping("/api/followToUser")
    public ResponseEntity<String> toSubscribe(@RequestParam Long followingTo, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        subscribeService.addSubscribeTo(user.getUsername(), followingTo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showAllSubscribes")
    public Slice<SubscribeDTO> findUserSubscribtions(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return subscribeService.findByEmail(user.getUsername(), pageable);
    }


}
