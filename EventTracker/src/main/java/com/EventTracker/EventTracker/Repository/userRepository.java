package com.EventTracker.EventTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EventTracker.EventTracker.Model.model;

public interface userRepository extends JpaRepository<model, Long> {
    
}
