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

    public void addComment(Long pubId, String useremail, String text) {
        var publicat = publicationRepository.findPublicationById(pubId);
        var user = userRepository.findByEmailContainsIgnoringCase(useremail);
        var comment = Comment.builder()
                .author(user)
                .publication(publicat)
                .commentText(text)
                .dateAdded(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
    }

    public boolean deleteAnyComment(Long commentId, Long publicId, String useremail) throws Exception {
        var user = userRepository.findByEmailContainsIgnoringCase(useremail);
        var publication = publicationRepository.findPublicationById(publicId);
        if (!publication.getAuthor().getId().equals(user.getId())) {
            throw new Exception("You can't delete comments on other user's posts");
        }
        commentRepository.deleteCommentByIdAndPublicationIdAndAuthorId(commentId, publicId, user.getId());
        return true;
    }

}
