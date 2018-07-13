package wx;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Homework", schema = "wx2", catalog = "")
public class HomeworkEntity {
    private int homeworkId;
    private String homeworkGroupId;
    private String homeworkContent;
    private Timestamp hwdate;
    private Timestamp deadline;
    private String publisherId;

    @Id
    @Column(name = "HomeworkID")
    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Basic
    @Column(name = "homeworkGroupID")
    public String getHomeworkGroupId() {
        return homeworkGroupId;
    }

    public void setHomeworkGroupId(String homeworkGroupId) {
        this.homeworkGroupId = homeworkGroupId;
    }

    @Basic
    @Column(name = "homeworkContent")
    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    @Basic
    @Column(name = "hwdate")
    public Timestamp getHwdate() {
        return hwdate;
    }

    public void setHwdate(Timestamp hwdate) {
        this.hwdate = hwdate;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "publisherID")
    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeworkEntity that = (HomeworkEntity) o;
        return homeworkId == that.homeworkId &&
                Objects.equals(homeworkGroupId, that.homeworkGroupId) &&
                Objects.equals(homeworkContent, that.homeworkContent) &&
                Objects.equals(hwdate, that.hwdate) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(publisherId, that.publisherId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(homeworkId, homeworkGroupId, homeworkContent, hwdate, deadline, publisherId);
    }
}
