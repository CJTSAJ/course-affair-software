package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VoteChooseEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int voteId;

    @Column(name = "studentID")
    @Id
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "studentGroupID")
    @Id
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Column(name = "voteID")
    @Id
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteChooseEntityPK that = (VoteChooseEntityPK) o;
        return voteId == that.voteId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, voteId);
    }
}
