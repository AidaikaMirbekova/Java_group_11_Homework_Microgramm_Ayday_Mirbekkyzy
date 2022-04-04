package com.example.micrigramm.Controller;

import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addNewComment")
    public ResponseEntity<String> addComment(@RequestParam Long publicId, String text, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        commentService.addComment(publicId, user.getUsername(), text);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<String> deletePublic(@PathVariable Long commentId, Long publicId, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        if (commentService.deleteAnyComment(commentId, publicId, user.getUsername())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
