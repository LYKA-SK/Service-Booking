package com.mindvault.online_service.dtos.request;

import com.mindvault.online_service.entities.RoleEnum;
import lombok.Data;

@Data
public class AssignRoleRequest {
    private Long userId;      // the user to update
    private RoleEnum role;    // the new role to assign
}
