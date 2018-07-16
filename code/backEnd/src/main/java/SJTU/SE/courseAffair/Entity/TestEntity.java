package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "test", schema = "course_affair_db")
public class TestEntity {
    private int testId;
    private String testGroupId;
    private String testContent;

    @Id
    @Column(name = "testID", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "testGroupID", nullable = true, length = 29)
    public String getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(String testGroupId) {
        this.testGroupId = testGroupId;
    }

    @Basic
    @Column(name = "testContent", nullable = true, length = 1024)
    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (testId != that.testId) return false;
        if (testGroupId != null ? !testGroupId.equals(that.testGroupId) : that.testGroupId != null) return false;
        if (testContent != null ? !testContent.equals(that.testContent) : that.testContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (testGroupId != null ? testGroupId.hashCode() : 0);
        result = 31 * result + (testContent != null ? testContent.hashCode() : 0);
        return result;
    }
}
