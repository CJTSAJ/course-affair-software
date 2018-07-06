package com.example.jpatry.controller;

import com.example.jpatry.domain.Notification;
import com.example.jpatry.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @RequestMapping("/getNotice")
    public List<Notification> getAll(@RequestBody String groupID){
        return notificationRepository.findByNotificationGroupID(groupID);
    }

    @RequestMapping(value="/addNotice")
    public void save(@RequestBody String openid,@RequestBody String content){


        Timestamp time= new Timestamp(System.currentTimeMillis());
        Notification notificationEntity= new Notification();
        notificationEntity.setNotificationContent(content);
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupID(openid);
        notificationEntity.setNotificationPublisherID(openid);
        notificationRepository.save(notificationEntity);
    }
}
