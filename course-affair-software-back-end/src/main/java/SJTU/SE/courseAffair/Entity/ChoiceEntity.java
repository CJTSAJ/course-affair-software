package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Choice", schema = "course_affair_DB")
@IdClass(ChoiceEntityPK.class)
public class ChoiceEntity {
    private int testId;
    private int questionId;
    private String choiceNo;
    private String choiceContent;

    @Id
    @Column(name = "testID", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Id
    @Column(name = "questionID", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Id
    @Column(name = "choice_No", nullable = false, length = 1)
    public String getChoiceNo() {
        return choiceNo;
    }

    public void setChoiceNo(String choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Basic
    @Column(name = "choice_Content", nullable = true, length = 1024)
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

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (choiceNo != null ? !choiceNo.equals(that.choiceNo) : that.choiceNo != null) return false;
        if (choiceContent != null ? !choiceContent.equals(that.choiceContent) : that.choiceContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionId;
        result = 31 * result + (choiceNo != null ? choiceNo.hashCode() : 0);
        result = 31 * result + (choiceContent != null ? choiceContent.hashCode() : 0);
        return result;
    }
}
