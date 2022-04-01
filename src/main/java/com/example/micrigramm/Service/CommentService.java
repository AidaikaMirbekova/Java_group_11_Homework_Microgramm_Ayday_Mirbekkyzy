package com.example.micrigramm.Service;

import com.example.micrigramm.Entity.Comment;
import com.example.micrigramm.Repository.CommentRepository;
import com.example.micrigramm.Repository.PublicationRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public void addComment(Long pubId, Long userId, String text) {
        var publicat = publicationRepository.findPublicationById(pubId);
        var user = userRepository.findUserById(userId);
        var comment = Comment.builder()
                .author(user)
                .publication(publicat)
                .commentText(text)
                .dateAdded(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
    }

    public boolean deleteAnyComment(Long commentId, Long publicId, Long userId) {
        var user = userRepository.findUserById(userId);
        commentRepository.deleteCommentByIdAndPublicationIdAndAuthorId(commentId, publicId, userId);
        return true;
    }

}
