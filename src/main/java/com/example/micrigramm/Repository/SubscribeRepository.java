package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.Subscribe;
import org.springframework.data.repository.CrudRepository;

public interface SubscribeRepository extends CrudRepository<Subscribe,Long> {
    boolean existsBySubscriberIdAndSubscriptionId(Long followId,Long subscriberId);
}
