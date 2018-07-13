package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AnswerEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int testId;
    private int questionId;

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

    @Column(name = "testID")
    @Id
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Column(name = "questionID")
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
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, testId, questionId);
    }
}
