package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.UserDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserDTO findUser(String userSearch) {
        var users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(userSearch) ||
                    user.getLogin().equalsIgnoreCase(userSearch) ||
                    user.getName().equalsIgnoreCase(userSearch)) {
                return UserDTO.from(user);
            }
        }
        return null;
    }

    public void register(UserDTO user) throws Exception {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new Exception("Have an account registered with this email");
        }
        userRepository.save(User.builder()
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .countSubscribers(0)
                .countPublications(0)
                .countSubscribes(0)
                .build());

    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.getByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}