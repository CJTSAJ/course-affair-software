package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Hw_Grade", schema = "course_affair_DB")
@IdClass(HwGradeEntityPK.class)
public class HwGradeEntity {
    private String studentId;
    private String studentGroupId;
    private int homeworkId;
    private String grade;

    @Id
    @Column(name = "studentID", nullable = false, length = 28)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "student_GroupID", nullable = false, length = 29)
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "HomeworkID", nullable = false)
    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 5)
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

        if (homeworkId != that.homeworkId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + homeworkId;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }
}
