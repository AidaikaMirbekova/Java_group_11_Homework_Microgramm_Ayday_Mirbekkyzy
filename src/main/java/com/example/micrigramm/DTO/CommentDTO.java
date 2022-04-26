package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDTO {

    public static CommentDTO from(Comment comment) {
        return builder()
                .id(comment.getId())
                .author(comment.getAuthor().getLogin())
                .publicationId(comment.getPublication().getId())
                .commentText(comment.getCommentText())
                .dateAdded(comment.getDateAdded())
                .build();
    }


    private Long id;
    private String commentText;
    private LocalDateTime dateAdded;
    private String author;
    private Long publicationId;
}
