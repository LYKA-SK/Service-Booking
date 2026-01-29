package com.mindvault.online_service.service;

import com.mindvault.online_service.entities.RoleEnum;
import com.mindvault.online_service.entities.User;

public interface UserService {
    User assignRole(Long userId, RoleEnum role);
}
