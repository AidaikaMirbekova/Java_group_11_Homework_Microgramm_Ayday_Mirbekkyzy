package com.example.micrigramm.Controller;

import com.example.micrigramm.Service.SubscribeService;
import com.example.micrigramm.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribes")
public class SubscribeController {
    private final UserService userService;
    private final SubscribeService subscribeService;

    public SubscribeController(UserService userService, SubscribeService subscribeService) {
        this.userService = userService;
        this.subscribeService = subscribeService;
    }

    @PostMapping("/toSubscribe")
    public ResponseEntity<String> toSubscribe(@RequestParam Long userId, Long followingTo) throws Exception {
        subscribeService.addSubscribeTo(userId, followingTo);
        return ResponseEntity.ok().build();
    }

}
