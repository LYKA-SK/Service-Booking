package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.entities.RoleEnum;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User assignRole(Long userId, RoleEnum role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);

        return userRepository.save(user);
    }
}
