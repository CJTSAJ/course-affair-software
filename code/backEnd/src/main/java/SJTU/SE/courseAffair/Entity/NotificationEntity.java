package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notification", schema = "course_affair_db")
public class NotificationEntity {
    private int notificationId;
    private String notificationGroupId;
    private String notificationContent;
    private Timestamp notificationDate;
    private String notificationPublisherId;

    @Id
    @Column(name = "notificationID", nullable = false)
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "notificationGroupID", nullable = true, length = 29)
    public String getNotificationGroupId() {
        return notificationGroupId;
    }

    public void setNotificationGroupId(String notificationGroupId) {
        this.notificationGroupId = notificationGroupId;
    }

    @Basic
    @Column(name = "notificationContent", nullable = true, length = 1024)
    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    @Basic
    @Column(name = "notificationDate", nullable = true)
    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    @Basic
    @Column(name = "notificationPublisherID", nullable = true, length = 28)
    public String getNotificationPublisherId() {
        return notificationPublisherId;
    }

    public void setNotificationPublisherId(String notificationPublisherId) {
        this.notificationPublisherId = notificationPublisherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEntity that = (NotificationEntity) o;

        if (notificationId != that.notificationId) return false;
        if (notificationGroupId != null ? !notificationGroupId.equals(that.notificationGroupId) : that.notificationGroupId != null)
            return false;
        if (notificationContent != null ? !notificationContent.equals(that.notificationContent) : that.notificationContent != null)
            return false;
        if (notificationDate != null ? !notificationDate.equals(that.notificationDate) : that.notificationDate != null)
            return false;
        if (notificationPublisherId != null ? !notificationPublisherId.equals(that.notificationPublisherId) : that.notificationPublisherId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = notificationId;
        result = 31 * result + (notificationGroupId != null ? notificationGroupId.hashCode() : 0);
        result = 31 * result + (notificationContent != null ? notificationContent.hashCode() : 0);
        result = 31 * result + (notificationDate != null ? notificationDate.hashCode() : 0);
        result = 31 * result + (notificationPublisherId != null ? notificationPublisherId.hashCode() : 0);
        return result;
    }
}
