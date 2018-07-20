package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Answer", schema = "course_affair_DB")
@IdClass(AnswerEntityPK.class)
public class AnswerEntity {
    private String studentId;
    private String studentGroupId;
    private int testId;
    private int questionId;
    private String answer;

    @Id
    @Column(name = "studentID", nullable = false, length = 28)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "student_GroupID", nullable = false, length = 29)
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

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
    @Column(name = "answer", nullable = true, length = 8)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (testId != that.testId) return false;
        if (questionId != that.questionId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + testId;
        result = 31 * result + questionId;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
