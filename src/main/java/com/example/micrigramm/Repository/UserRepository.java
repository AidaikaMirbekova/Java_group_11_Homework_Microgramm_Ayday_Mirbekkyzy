package com.example.micrigramm.Repository;

import com.example.micrigramm.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getByEmail(String email);

    User findByEmailContainsIgnoringCase(String email);

    User findUserById(Long id);

    boolean existsUserByEmail(String email);


}
