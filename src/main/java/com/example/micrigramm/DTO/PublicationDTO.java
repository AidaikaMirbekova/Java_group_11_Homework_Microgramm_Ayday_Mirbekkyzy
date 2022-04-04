package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Publication;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    public static PublicationDTO from(Publication publication) {
        return builder()
                .id(publication.getId())
                .image(publication.getImage())
                .description(publication.getDescription())
                .authorId(publication.getAuthor().getId())
                .dateAdded(LocalDateTime.now())
                .build();
    }

    private Long id;
    private byte[] image;
    private String description;
    private Long authorId;
    private LocalDateTime dateAdded;


}
