package com.example.micrigramm.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "like_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "like_owner_id")
    private User likeOwner;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;
}
