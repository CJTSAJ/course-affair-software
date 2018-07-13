package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CorrectAnswer", schema = "wx2", catalog = "")
@IdClass(CorrectAnswerEntityPK.class)
public class CorrectAnswerEntity {
    private int testId;
    private int questionId;
    private String correctAns;

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
    @Column(name = "correctAns")
    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorrectAnswerEntity that = (CorrectAnswerEntity) o;
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(correctAns, that.correctAns);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, questionId, correctAns);
    }
}
