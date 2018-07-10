package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Homework", schema = "course_affair_DB")
public class HomeworkEntity {
    private int homeworkId;
    private String homeworkGroupId;
    private String homeworkContent;
    private Timestamp hwdate;
    private Timestamp deadline;
    private String publisherId;

    @Id
    @Column(name = "HomeworkID", nullable = false)
    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Basic
    @Column(name = "homework_GroupID", nullable = true, length = 28)
    public String getHomeworkGroupId() {
        return homeworkGroupId;
    }

    public void setHomeworkGroupId(String homeworkGroupId) {
        this.homeworkGroupId = homeworkGroupId;
    }

    @Basic
    @Column(name = "homework_Content", nullable = true, length = 1024)
    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    @Basic
    @Column(name = "hwdate", nullable = true)
    public Timestamp getHwdate() {
        return hwdate;
    }

    public void setHwdate(Timestamp hwdate) {
        this.hwdate = hwdate;
    }

    @Basic
    @Column(name = "deadline", nullable = true)
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "publisherID", nullable = true, length = 28)
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

        if (homeworkId != that.homeworkId) return false;
        if (homeworkGroupId != null ? !homeworkGroupId.equals(that.homeworkGroupId) : that.homeworkGroupId != null)
            return false;
        if (homeworkContent != null ? !homeworkContent.equals(that.homeworkContent) : that.homeworkContent != null)
            return false;
        if (hwdate != null ? !hwdate.equals(that.hwdate) : that.hwdate != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;
        if (publisherId != null ? !publisherId.equals(that.publisherId) : that.publisherId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = homeworkId;
        result = 31 * result + (homeworkGroupId != null ? homeworkGroupId.hashCode() : 0);
        result = 31 * result + (homeworkContent != null ? homeworkContent.hashCode() : 0);
        result = 31 * result + (hwdate != null ? hwdate.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (publisherId != null ? publisherId.hashCode() : 0);
        return result;
    }
}
