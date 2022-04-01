package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmailContainsIgnoringCase(String email);
    User findUserById(Long id);
}
