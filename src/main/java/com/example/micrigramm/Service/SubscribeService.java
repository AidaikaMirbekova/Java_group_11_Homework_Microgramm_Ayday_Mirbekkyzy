package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.SubscribeDTO;
import com.example.micrigramm.Entity.Subscribe;
import com.example.micrigramm.Repository.SubscribeRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    public SubscribeDTO addSubscribeTo(Long subscribers, Long toSubcribes) throws Exception {
        var follower = userRepository.findUserById(subscribers);
        var toFoloow = userRepository.findUserById(toSubcribes);
        if (subscribeRepository.existsBySubscriberIdAndSubscriptionId(subscribers,toSubcribes)) {
            throw new Exception("You are already following this user");
        }
        var subscriberTo = Subscribe.builder()
                .subscriber(follower)
                .subscription(toFoloow)
                .dateAdded(LocalDateTime.now())
                .build();
        Integer counter = follower.getCountSubscribes();
        follower.setCountSubscribes(counter + 1);
        Integer counterMyFollower = toFoloow.getCountSubscribers();
        toFoloow.setCountSubscribers(counterMyFollower + 1);

        subscribeRepository.save(subscriberTo);
        userRepository.save(follower);
        userRepository.save(toFoloow);
        return SubscribeDTO.from(subscriberTo);
    }

}
