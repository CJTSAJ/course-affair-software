package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Test", schema = "wx2", catalog = "")
public class TestEntity {
    private int testId;
    private String testGroupId;
    private String testContent;

    @Id
    @Column(name = "testID")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "testGroupID")
    public String getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(String testGroupId) {
        this.testGroupId = testGroupId;
    }

    @Basic
    @Column(name = "testContent")
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
        return testId == that.testId &&
                Objects.equals(testGroupId, that.testGroupId) &&
                Objects.equals(testContent, that.testContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, testGroupId, testContent);
    }
}
