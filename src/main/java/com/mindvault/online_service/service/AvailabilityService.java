package com.mindvault.online_service.service;

import com.mindvault.online_service.entities.Availability;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.AvailabilityRepository;
import com.mindvault.online_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvailabilityService {
    @Autowired private AvailabilityRepository availabilityRepository;
    @Autowired private UserRepository userRepository;

    public void saveAvailability(String email, List<Availability> slots) {
        User provider = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Provider not found"));
            
        for (Availability slot : slots) {
            slot.setProvider(provider); 
        }
        availabilityRepository.saveAll(slots);
    }
}