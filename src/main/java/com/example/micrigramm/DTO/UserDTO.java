package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .countSubscribers(user.getCountSubscribers())
                .countSubscribes(user.getCountSubscribes())
                .countPublication(user.getCountPublications())
                .build();
    }

    private Long id;
    private String name;
    private String login;
    private String email;
    private Integer countPublication;
    private Integer countSubscribers;
    private Integer countSubscribes;
}
