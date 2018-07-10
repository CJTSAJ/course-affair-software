package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    public List<NotificationEntity> getAllBy();
    public NotificationEntity findByNotificationId(int Id);
    public List<NotificationEntity> findByNotificationGroupId(String groupId);
}
