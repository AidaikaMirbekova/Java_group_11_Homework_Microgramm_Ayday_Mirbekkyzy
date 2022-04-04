package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.SubscribeDTO;
import com.example.micrigramm.Entity.Subscribe;
import com.example.micrigramm.Repository.SubscribeRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    public SubscribeDTO addSubscribeTo(String subscribers, Long toSubcribes) throws Exception {
        var follower = userRepository.findByEmailContainsIgnoringCase(subscribers);
        var toFoloow = userRepository.findUserById(toSubcribes);
        if (subscribeRepository.existsBySubscriberIdAndSubscriptionId(follower.getId(), toSubcribes)) {
            throw new Exception("You are already following this user");
        }
        var subscriberTo = Subscribe.builder()
                .subscriber(follower)
                .subscription(toFoloow)
                .dateAdded(LocalDateTime.now())
                .build();
        Integer counterFollower = 1;
        follower.setCountSubscribes(follower.getCountSubscribes() + counterFollower);

        Integer counterToFollow = 1;
        toFoloow.setCountSubscribers(toFoloow.getCountSubscribers() + counterToFollow);

        subscribeRepository.save(subscriberTo);
        userRepository.save(follower);
        userRepository.save(toFoloow);
        return SubscribeDTO.from(subscriberTo);
    }

    public Slice<SubscribeDTO> findByEmail(String email, Pageable pageable) {
        var subscriptions = subscribeRepository.findAllBySubscriberEmail(email, pageable);
        return subscriptions.map(SubscribeDTO::from);
    }
}
