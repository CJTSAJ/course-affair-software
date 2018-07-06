package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Vote_Choose", schema = "course_affair_DB")
@IdClass(VoteChooseEntityPK.class)
public class VoteChooseEntity {
    private String studentId;
    private String studentGroupId;
    private int voteId;
    private String voteChoose;

    @Id
    @Column(name = "studentID", nullable = false, length = 28)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "student_GroupID", nullable = false, length = 28)
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "voteID", nullable = false)
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "vote_Choose", nullable = true, length = 8)
    public String getVoteChoose() {
        return voteChoose;
    }

    public void setVoteChoose(String voteChoose) {
        this.voteChoose = voteChoose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteChooseEntity that = (VoteChooseEntity) o;

        if (voteId != that.voteId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;
        if (voteChoose != null ? !voteChoose.equals(that.voteChoose) : that.voteChoose != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + voteId;
        result = 31 * result + (voteChoose != null ? voteChoose.hashCode() : 0);
        return result;
    }
}
