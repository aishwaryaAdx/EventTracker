package com.EventTracker.EventTracker.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EventTracker.EventTracker.Model.model;
import com.EventTracker.EventTracker.Repository.userRepository;

@Service
public class userService {

    public userService(userRepository repo) {
        this.repo = repo;
    }

    @Autowired
    private final userRepository repo;

    
    public void dataLog(String sessionId, String page, String referer, String ipAddress, String deviceID, String utmSource, String utmMedium, String utmCampaign) {
        model record = model.builder()
                .sessionId(sessionId)
                .pageVisited(page)
                .referer(referer)
                .timeStamp(LocalDateTime.now())
                .ipAddress(ipAddress)
                .deviceID(deviceID)
                .utmSource(utmSource)
                .utmMedium(utmMedium)
                .utmCampaign(utmCampaign)
                .build();
        repo.save(record);
    }
    
}
