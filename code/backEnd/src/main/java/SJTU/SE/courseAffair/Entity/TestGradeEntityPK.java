package SJTU.SE.courseAffair.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TestGradeEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int testId;


    @Column(name = "studentID", nullable = false, length = 28)
    @Id
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "studentGroupID", nullable = false, length = 29)
    @Id
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "testID", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestGradeEntityPK that = (TestGradeEntityPK) o;

        if (testId != that.testId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + testId;
        return result;
    }



}
