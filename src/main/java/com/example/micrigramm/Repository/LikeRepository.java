package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.Like;
import org.springframework.data.repository.CrudRepository;


public interface LikeRepository extends CrudRepository<Like, Long> {
    void deleteLikeByPublicationId(Long pubId);
    Boolean existsLikeByLikeOwnerIdAndPublicationId(Long userId, Long publicId);
}
