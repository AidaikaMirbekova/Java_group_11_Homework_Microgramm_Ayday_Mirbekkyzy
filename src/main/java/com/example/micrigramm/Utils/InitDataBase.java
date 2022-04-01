package com.example.micrigramm.Utils;

import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitDataBase {
    @Bean
    public CommandLineRunner init(UserRepository userRepo,
                                  LikeRepository likeRepo,
                                  SubscribeRepository subscriptionRepo,
                                  PublicationRepository publicationsRepo,
                                  CommentRepository commentRepo) {

        return (args) -> {
            List<User> users =new ArrayList<>();
            users.add(new User(1L,"Ayday","aika","qwe@qwe","123",1,1,1));
            users.add(new User(2L,"Mika","mike","mike@qwe","123",1,1,1));

            userRepo.saveAll(users);

        };
    }
}
