package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Publication;
import lombok.*;

import java.time.LocalDate;
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
                .author(publication.getAuthor().getLogin())
                .dateAdded(publication.getDateAdded())
                .build();
    }

    private Long id;
    private byte[] image;
    private String description;
    private String author;
    private LocalDate dateAdded;


}
