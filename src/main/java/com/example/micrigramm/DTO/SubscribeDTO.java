package com.example.micrigramm.DTO;

import com.example.micrigramm.Entity.Subscribe;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscribeDTO {

    public static SubscribeDTO from(Subscribe subscribe) {
        return builder()
                .id(subscribe.getId())
                .subscriber(subscribe.getSubscriber().getId())
                .subscription(subscribe.getSubscription().getId())
                .dateAdded(LocalDateTime.now())
                .build();
    }

    private Long id;
    private Long subscriber;
    private Long subscription;
    private LocalDateTime dateAdded;

}
