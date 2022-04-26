package com.example.micrigramm.Controller;

import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342", maxAge = 36000)
@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/addLike")
    public ResponseEntity<String> addLike(@RequestParam Long publicId, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        likeService.likePublication(user.getUsername(), publicId);
        return ResponseEntity.ok().build();
    }
}
