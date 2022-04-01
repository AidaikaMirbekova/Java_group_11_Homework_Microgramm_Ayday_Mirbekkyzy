package com.example.micrigramm.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscribe")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private User subscription;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;
}
