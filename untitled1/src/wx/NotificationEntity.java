package wx;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Notification", schema = "wx2", catalog = "")
public class NotificationEntity {
    private int notificationId;
    private String notificationGroupId;
    private String notificationContent;
    private Timestamp notificationDate;
    private String notificationPublisherId;

    @Id
    @Column(name = "notificationID")
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "notificationGroupID")
    public String getNotificationGroupId() {
        return notificationGroupId;
    }

    public void setNotificationGroupId(String notificationGroupId) {
        this.notificationGroupId = notificationGroupId;
    }

    @Basic
    @Column(name = "notificationContent")
    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    @Basic
    @Column(name = "notificationDate")
    public Timestamp getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    @Basic
    @Column(name = "notificationPublisherID")
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
        return notificationId == that.notificationId &&
                Objects.equals(notificationGroupId, that.notificationGroupId) &&
                Objects.equals(notificationContent, that.notificationContent) &&
                Objects.equals(notificationDate, that.notificationDate) &&
                Objects.equals(notificationPublisherId, that.notificationPublisherId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(notificationId, notificationGroupId, notificationContent, notificationDate, notificationPublisherId);
    }
}
