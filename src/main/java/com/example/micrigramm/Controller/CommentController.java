package com.example.micrigramm.Controller;

import com.example.micrigramm.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestParam Long publicId,Long userId, String text) {
        commentService.addComment(publicId,userId,text);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deletePublic(@PathVariable Long commentId,Long publicId ,Long usarId) throws Exception {
        if (commentService.deleteAnyComment(commentId,publicId,usarId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
