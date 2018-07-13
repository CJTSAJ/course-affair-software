package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ChoiceEntityPK implements Serializable {
    private int testId;
    private int questionId;
    private String choiceNo;

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

    @Column(name = "choiceNo")
    @Id
    public String getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(String choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceEntityPK that = (ChoiceEntityPK) o;
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(choiceNo, that.choiceNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, questionId, choiceNo);
    }
}
