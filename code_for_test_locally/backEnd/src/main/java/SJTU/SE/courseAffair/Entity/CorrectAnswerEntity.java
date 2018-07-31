package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Correct_Answer", schema = "course_affair_DB")
@IdClass(CorrectAnswerEntityPK.class)
public class CorrectAnswerEntity {
    private int testId;
    private int questionId;
    private int correctAns;

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
    @Column(name = "correct_Ans", nullable = false)
    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorrectAnswerEntity that = (CorrectAnswerEntity) o;

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (correctAns != that.correctAns) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionId;
        result = 31 * result + correctAns;
        return result;
    }
}