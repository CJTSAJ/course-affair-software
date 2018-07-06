package SJTU.SE.courseAffair.Action;


import SJTU.SE.courseAffair.Dao.NotificationRepository;
import SJTU.SE.courseAffair.Entity.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateController {


    @Autowired
    private NotificationRepository userRepository;


    @RequestMapping("getAll")
    @ResponseBody
    public List<NotificationEntity> getAll(){
        return userRepository.getAllBy();
    }

    @RequestMapping("getById")
    @ResponseBody
    public NotificationEntity getById(int Id){
        return userRepository.findByNotificationId(Id);
    }

    @RequestMapping("save")
    @ResponseBody
    public void save(){


        Timestamp time= new Timestamp(System.currentTimeMillis());
        NotificationEntity notificationEntity= new NotificationEntity();
        notificationEntity.setNotificationContent("haha");
        notificationEntity.setNotificationDate(time);
        notificationEntity.setNotificationGroupId("12345");
        notificationEntity.setNotificationPublisherId("12345");
        userRepository.save(notificationEntity);
    }

}
