package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CorrectAnswerEntityPK implements Serializable {
    private int testId;
    private int questionId;

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
        CorrectAnswerEntityPK that = (CorrectAnswerEntityPK) o;
        return testId == that.testId &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, questionId);
    }
}
