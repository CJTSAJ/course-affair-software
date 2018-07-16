package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "correctanswer", schema = "course_affair_db")
@IdClass(CorrectAnswerEntityPK.class)
public class CorrectAnswerEntity {
    private int testId;
    private int questionId;
    private String correctAns;

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

    @Basic
    @Column(name = "correctAns", nullable = true, length = 8)
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

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (correctAns != null ? !correctAns.equals(that.correctAns) : that.correctAns != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionId;
        result = 31 * result + (correctAns != null ? correctAns.hashCode() : 0);
        return result;
    }
}
