package com.mindvault.online_service.dtos.request;

import lombok.Data;

@Data
public class ProviderRoleRequest {

    private String serviceType;     // PLUMBER, CLEANING, ELECTRICIAN
    private String description;     // experience / explanation
}
