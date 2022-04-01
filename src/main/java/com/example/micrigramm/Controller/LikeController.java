package com.example.micrigramm.Controller;

import com.example.micrigramm.Service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/addLike")
    public ResponseEntity<String> addLike(@RequestParam Long userId,Long publicId) throws Exception {
        likeService.likePublication(userId,publicId);
        return ResponseEntity.ok().build();
    }
}
