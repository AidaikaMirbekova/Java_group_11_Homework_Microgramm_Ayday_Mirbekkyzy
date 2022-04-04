package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.Subscribe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface SubscribeRepository extends PagingAndSortingRepository<Subscribe, Long> {
    boolean existsBySubscriberIdAndSubscriptionId(Long followId, Long subscriberId);

    Slice<Subscribe> findAllBySubscriberEmail(String email, Pageable pageable);
}
