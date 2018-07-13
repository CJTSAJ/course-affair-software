package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HwGradeEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int homeworkId;

    @Column(name = "studentID")
    @Id
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "studentGroupID")
    @Id
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Column(name = "HomeworkID")
    @Id
    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwGradeEntityPK that = (HwGradeEntityPK) o;
        return homeworkId == that.homeworkId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, homeworkId);
    }
}
