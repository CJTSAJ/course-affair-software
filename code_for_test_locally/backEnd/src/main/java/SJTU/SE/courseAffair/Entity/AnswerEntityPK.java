package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AnswerEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int testId;
    private int questionId;

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

    @Column(name = "testID", nullable = false)
    @Id
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Column(name = "questionID", nullable = false)
    @Id
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntityPK that = (AnswerEntityPK) o;

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
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
        result = 31 * result + questionId;
        return result;
    }
}
