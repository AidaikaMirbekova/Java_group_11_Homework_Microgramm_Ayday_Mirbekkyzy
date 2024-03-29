package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.CommentDTO;
import com.example.micrigramm.Entity.Comment;
import com.example.micrigramm.Repository.CommentRepository;
import com.example.micrigramm.Repository.PublicationRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public void addComment(CommentDTO dto, String useremail) {
        var publicat = publicationRepository.findPublicationById(dto.getPublicationId());
        var user = userRepository.findByEmailContainsIgnoringCase(useremail);
        var comment = Comment.builder()
                .author(user)
                .publication(publicat)
                .commentText(dto.getCommentText())
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

    public List<CommentDTO> showComentsByPublic(Long pubId){
        List<Comment> comments= commentRepository.findCommentsByPublicationId(pubId);
        return comments.stream().map(CommentDTO::from).collect(Collectors.toList());
    }
}
