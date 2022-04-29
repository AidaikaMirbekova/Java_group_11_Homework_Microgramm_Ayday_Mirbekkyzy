package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.CommentDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5501", maxAge = 36000)
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addNewComment")
    public ResponseEntity<String> addComment(@RequestBody CommentDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        commentService.addComment(dto, user.getUsername());
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

    @GetMapping("/showComments/{pubId}")
    public List<CommentDTO> showComments(@PathVariable Long pubId){
        return commentService.showComentsByPublic(pubId);
    }
}
