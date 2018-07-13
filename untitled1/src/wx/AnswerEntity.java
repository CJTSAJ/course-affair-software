package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Answer", schema = "wx2", catalog = "")
@IdClass(AnswerEntityPK.class)
public class AnswerEntity {
    private String studentId;
    private String studentGroupId;
    private int testId;
    private int questionId;
    private String answer;

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
    @Column(name = "testID")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Id
    @Column(name = "questionID")
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, testId, questionId, answer);
    }
}
