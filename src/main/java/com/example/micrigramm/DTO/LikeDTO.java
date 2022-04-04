package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Like;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {
    private Long id;
    private Long likeOwnerId;
    private Long publicationId;
    private LocalDateTime dateAdded;

    public static LikeDTO from(Like like) {
        return builder()
                .id(like.getId())
                .likeOwnerId(like.getLikeOwner().getId())
                .publicationId(like.getPublication().getId())
                .dateAdded(LocalDateTime.now())
                .build();
    }
}
