package com.example.micrigramm.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    @JoinColumn(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "date_added")
    private LocalDate dateAdded;

}
