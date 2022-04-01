package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Comment;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class CommentDTO {

    public static CommentDTO from(Comment comment){
        return builder()
                .id(comment.getId())
                .authorId(comment.getAuthor().getId())
                .publicationId(comment.getPublication().getId())
                .commentText(comment.getCommentText())
                .dateAdded(LocalDateTime.now())
                .build();
    }

    private Long id;
    private String commentText;
    private LocalDateTime dateAdded;
    private Long authorId;
    private Long publicationId;
}
