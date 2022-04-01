package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.UserDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDTO findUser(String userSearch) {
        var users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(userSearch) ||
                    user.getLogin().equals(userSearch) ||
                    user.getName().equals(userSearch)) {
                return UserDTO.from(user);
            }
        }
        return null;
    }
}