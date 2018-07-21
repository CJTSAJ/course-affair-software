package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Question", schema = "course_affair_DB")
@IdClass(QuestionEntityPK.class)
public class QuestionEntity {
    private int testId;
    private int questionId;
    private String questionContent;
    private int point;

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
    @Column(name = "question_Content", nullable = true, length = 1024)
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Basic
    @Column(name = "point", nullable = true)
    public int getPoint() { return point;}

    public void setPoint(int point) { this.point = point; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (questionContent != null ? !questionContent.equals(that.questionContent) : that.questionContent != null)
            return false;
        if(point != that.point) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionId;
        result = 31 * result + (questionContent != null ? questionContent.hashCode() : 0);
        result = 31 * result + point;
        return result;
    }
}
