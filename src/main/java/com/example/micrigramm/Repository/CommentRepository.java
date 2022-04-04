package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    void deleteCommentsByPublicationId(Long publicId);

    void deleteCommentByIdAndPublicationIdAndAuthorId(Long commentId, Long pubId, Long userId);
}
