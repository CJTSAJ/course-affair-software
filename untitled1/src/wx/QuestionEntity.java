package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Question", schema = "wx2", catalog = "")
@IdClass(QuestionEntityPK.class)
public class QuestionEntity {
    private int testId;
    private int questionId;
    private String questionContent;

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
    @Column(name = "questionContent")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return testId == that.testId &&
                questionId == that.questionId &&
                Objects.equals(questionContent, that.questionContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testId, questionId, questionContent);
    }
}
