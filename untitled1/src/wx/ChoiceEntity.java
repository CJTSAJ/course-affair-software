package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Choice", schema = "wx2", catalog = "")
@IdClass(ChoiceEntityPK.class)
public class ChoiceEntity {
    private int testId;
    private int questionId;
    private String choiceNo;
    private String choiceContent;

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

    @Id
    @Column(name = "choiceNo")
    public String getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(String choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Basic
    @Column(name = "choiceContent")
    public String getChoiceContent() {
        return choiceContent;
    }

    public void setChoiceContent(String choiceContent) {
        this.choiceContent = choiceContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceEntity that = (ChoiceEntity) o;
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(choiceNo, that.choiceNo) &&
                Objects.equals(choiceContent, that.choiceContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, questionId, choiceNo, choiceContent);
    }
}
