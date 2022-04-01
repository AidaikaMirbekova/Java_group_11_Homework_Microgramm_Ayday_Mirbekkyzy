package com.example.micrigramm.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publication")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "image_id")
    private byte[] image;

    @JoinColumn(name = "description_id")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

}
