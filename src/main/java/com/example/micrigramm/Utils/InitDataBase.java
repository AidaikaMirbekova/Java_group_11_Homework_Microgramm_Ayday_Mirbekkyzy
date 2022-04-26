package com.example.micrigramm.Utils;

import com.example.micrigramm.Entity.*;
import com.example.micrigramm.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Configuration
public class InitDataBase {
    UserRepository userRepository;
    PublicationRepository publicationRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    SubscribeRepository subscribeRepository;
    PasswordEncoder encoder;
    @Bean
    public CommandLineRunner init() {
        return (args) -> {generateAndInsertData();};}

    private void generateAndInsertData() {
        likeRepository.deleteAll();
        commentRepository.deleteAll();
        subscribeRepository.deleteAll();
        publicationRepository.deleteAll();
        userRepository.deleteAll();


            userRepository.deleteAll();
            User user = new User();
            user.setEmail("test@test");
            user.setLogin("Ayday123");
            user.setName("Koko");
            user.setCountSubscribes(0);
            user.setCountSubscribers(0);
            user.setCountPublications(0);
            user.setPassword(encoder.encode("test"));


            User user1 = new User();
            user1.setEmail("guest@qwe");
            user1.setLogin("Mike");
            user1.setName("Lora");
            user1.setCountSubscribes(0);
            user1.setCountSubscribers(0);
            user1.setCountPublications(0);
            user1.setPassword(encoder.encode("guest"));

            User user2 = new User();
            user2.setEmail("admin@test");
            user2.setLogin("Kolandr");
            user2.setName("Lira");
            user2.setCountSubscribes(0);
            user2.setCountSubscribers(0);
            user2.setCountPublications(0);
            user2.setPassword(encoder.encode("admin"));

            List<User> users= new ArrayList<>();
            users.add(user);
            users.add(user1);
            users.add(user2);



            Random r = new Random();

//-----------------------------------
            List<Subscribe> subs = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                List<User> tmp = new ArrayList<>();
                tmp.addAll(users);
                tmp.remove(i);
                for (int j = r.nextInt(2) + 1; j > 0; j--) {
                    var s = tmp.remove(r.nextInt(tmp.size()));
                    subs.add(Subscribe.builder()
                            .subscriber(u)
                            .subscription(s)
                            .dateAdded(LocalDateTime.now().plusMinutes(r.nextInt(10)))
                            .build()
                    );
                    u.setCountSubscribes(u.getCountSubscribes() + 1);
                    s.setCountSubscribers(s.getCountSubscribers() + 1);
                }
            }



//-----------------------------------
            byte[] data = new byte[0];
            try {
                Path path = Paths.get("src/main/resources/Image.jpg");
                data = Files.readAllBytes(path);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Publication> publications = new ArrayList<>();
            for (int i = 1; i < 21; i++) {
                User author = users.get(r.nextInt(users.size()));
                publications.add(Publication.builder()
                        .image(data)
                        .description(Generator.makeDescription())
                        .author(author)
                        .dateAdded(LocalDate.now().plusDays(i))
                        .build()
                );
                author.setCountPublications(author.getCountPublications() + 1);
            }


//-----------------------------------
            List<Like> likes = new ArrayList<>();
            for (User u : users) {
                List<Publication> tmp = new ArrayList<>();
                tmp.addAll(publications);
                for (int i = r.nextInt(5); i > 0; i--) {
                    Publication p = tmp.remove(r.nextInt(tmp.size()));
                    likes.add(Like.builder()
                            .likeOwner(u)
                            .publication(p)
                            .dateAdded(LocalDateTime.now().plusMinutes(10 + i))
                            .build());
                }
            }

//-----------------------------------
            List<Comment> comments = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                comments.add(
                        Comment.builder()
                                .author(users.get(r.nextInt(users.size())))
                                .publication(publications.get(r.nextInt(publications.size())))
                                .commentText(Generator.makeDescription())
                                .dateAdded(LocalDateTime.now().plusMinutes(60 + i))
                                .build()
                );
            }
            userRepository.saveAll(users);
            subscribeRepository.saveAll(subs);
            publicationRepository.saveAll(publications);
            likeRepository.saveAll(likes);
            commentRepository.saveAll(comments);

        }

    }
