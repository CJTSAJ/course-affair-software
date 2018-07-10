package com.example.jpatry.repository;

import com.example.jpatry.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    List<Notification> findByNotificationGroupID(String gID);
}
