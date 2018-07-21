package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Test", schema = "course_affair_DB")
public class TestEntity {
    private int testId;
    private String testGroupId;
    private String testContent;
    private Timestamp startTime;
    private Timestamp endTime;

    @Id
    @Column(name = "testID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "test_GroupID", nullable = true, length = 29)
    public String getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(String testGroupId) {
        this.testGroupId = testGroupId;
    }

    @Basic
    @Column(name = "test_Content", nullable = true,  unique = true, length = 1024)
    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    @Basic
    @Column(name = "test_StartTime", nullable = true)
    public Timestamp getStartTime() {return startTime;}

    public void setStartTime(Timestamp startTime) {this.startTime = startTime;}


    @Basic
    @Column(name = "test_EndTime", nullable = true)
    public Timestamp getEndTime() {return endTime;}

    public void setEndTime(Timestamp endTime) {this.endTime = endTime;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (testId != that.testId) return false;
        if (testGroupId != null ? !testGroupId.equals(that.testGroupId) : that.testGroupId != null) return false;
        if (testContent != null ? !testContent.equals(that.testContent) : that.testContent != null) return false;
        if (endTime != that.endTime) return false;
        if (startTime != that.startTime) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (testGroupId != null ? testGroupId.hashCode() : 0);
        result = 31 * result + (testContent != null ? testContent.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
