package com.EventTracker.EventTracker.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Event_Data")
public class model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId;
    private String referer;
    private String pageVisited;
    private LocalDateTime timeStamp;
    private String  ipAddress;
    private String  deviceID;
    private String utmSource;
    private String utmMedium;
    private String utmCampaign;
}
