package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HwGrade", schema = "wx2", catalog = "")
@IdClass(HwGradeEntityPK.class)
public class HwGradeEntity {
    private String studentId;
    private String studentGroupId;
    private int homeworkId;
    private String grade;

    @Id
    @Column(name = "studentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "studentGroupID")
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "HomeworkID")
    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwGradeEntity that = (HwGradeEntity) o;
        return homeworkId == that.homeworkId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, homeworkId, grade);
    }
}
