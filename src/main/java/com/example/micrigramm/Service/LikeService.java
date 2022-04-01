package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.LikeDTO;
import com.example.micrigramm.Entity.Like;
import com.example.micrigramm.Repository.LikeRepository;
import com.example.micrigramm.Repository.PublicationRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeService {
private final UserRepository userRepository;
private final LikeRepository likeRepository;
private final PublicationRepository publicationRepository;
    public LikeDTO likePublication(Long userId, Long publicId) throws Exception {
        var user=userRepository.findUserById(userId);
        var publication=publicationRepository.findPublicationById(publicId);
        if (likeRepository.existsLikeByLikeOwnerIdAndPublicationId(userId,publicId)) {
            throw new Exception("You already liked");
        }
            var like= Like.builder()
                    .likeOwner(user)
                    .publication(publication)
                    .dateAdded(LocalDateTime.now())
                    .build();
            likeRepository.save(like);
            return LikeDTO.from(like);

    }
}
