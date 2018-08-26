package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    public List<NotificationEntity> getAllBy();
    public NotificationEntity findByNotificationId(int Id);
    public List<NotificationEntity> findByNotificationGroupIdOrderByNotificationDateDesc(String groupId);
    
    @Transactional
	@Query(value = "select * from notification where notification_groupid=?1 order by notification_date desc limit 3", nativeQuery = true)
	@Modifying
	public List<NotificationEntity> findRecentNotification(String notification_groupid);
}
