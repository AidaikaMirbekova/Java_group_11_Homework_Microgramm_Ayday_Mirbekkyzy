package com.example.micrigramm.Utils;

import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
public class InitDataBase {

    @Bean
    public CommandLineRunner init(UserRepository userRepository,
                                  PasswordEncoder encoder) {
        return (args) -> {

            userRepository.deleteAll();
            User user = new User();
            user.setEmail("test@test");
            user.setLogin("aika");
            user.setName("Koko");
            user.setCountSubscribes(0);
            user.setCountSubscribers(0);
            user.setCountPublications(0);
            user.setPassword(encoder.encode("test"));
            userRepository.save(user);

            User user1 = new User();
            user1.setEmail("guest@qwe");
            user1.setLogin("Mike");
            user1.setName("Lora");
            user1.setCountSubscribes(0);
            user1.setCountSubscribers(0);
            user1.setCountPublications(0);
            user1.setPassword(encoder.encode("guest"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setEmail("admin@test");
            user2.setLogin("Mike");
            user2.setName("Lira");
            user2.setCountSubscribes(0);
            user2.setCountSubscribers(0);
            user2.setCountPublications(0);
            user2.setPassword(encoder.encode("admin"));
            userRepository.save(user2);

        };
    }
}
