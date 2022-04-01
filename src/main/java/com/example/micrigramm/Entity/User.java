package com.example.micrigramm.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String email;
    private String password;

    @Column(name = "count_publications")
    private Integer countPublications;

    @Column(name = "count_subscribers")
    private Integer countSubscribers;

    @Column(name = "count_subscribes")
    private Integer countSubscribes;

}
