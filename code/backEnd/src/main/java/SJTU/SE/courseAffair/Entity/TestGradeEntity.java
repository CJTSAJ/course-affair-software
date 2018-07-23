package SJTU.SE.courseAffair.Entity;


import javax.persistence.*;

@Entity
@Table(name = "Test_Grade", schema = "course_affair_DB")
@IdClass(TestGradeEntityPK.class)
public class TestGradeEntity {
    private String studentId;
    private String studentGroupId;
    private int testId;
    private int grade;

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
    @Column(name = "testID", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "grade", nullable = true)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestGradeEntity that = (TestGradeEntity) o;

        if (testId != that.testId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;
        if (grade != that.grade) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + testId;
        result = 31 * result + grade;
        return result;
    }


}
