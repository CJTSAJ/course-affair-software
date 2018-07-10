package com.example.jpatry.controller;

import com.example.jpatry.domain.Notification;
import com.example.jpatry.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @CrossOrigin
    @RequestMapping(value="/getNotice",method = RequestMethod.POST)
    public List<Notification> getAll(@RequestBody JSONObject data){
        String groupID=data.getString("openid");
        return notificationRepository.findByNotificationGroupID(groupID);
    }

    @CrossOrigin
    @RequestMapping(value="/addNotice",method = RequestMethod.POST)
    public void save(@RequestBody JSONObject data){

        String openid=data.getString("openid");
        String content=data.getString("content");
        Timestamp time= new Timestamp(System.currentTimeMillis());
        Notification notificationEntity= new Notification();
        notificationEntity.setNotificationContent(content);
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupID(openid);
        notificationEntity.setNotificationPublisherID(openid);
        notificationRepository.save(notificationEntity);
    }
}
