package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ChoiceEntityPK implements Serializable {
    private int testId;
    private int questionId;
    private int choiceNo;

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

    @Column(name = "choiceNo", nullable = false)
    @Id
    public int getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(int choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChoiceEntityPK that = (ChoiceEntityPK) o;

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (choiceNo != that.choiceNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionId;
        result = 31 * result + choiceNo;
        return result;
    }
}
