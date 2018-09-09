package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HwGradeEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int homeworkId;

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

    @Column(name = "HomeworkID", nullable = false)
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

        if (homeworkId != that.homeworkId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + homeworkId;
        return result;
    }
}
