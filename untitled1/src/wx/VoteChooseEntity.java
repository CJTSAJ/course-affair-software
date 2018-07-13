package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VoteChoose", schema = "wx2", catalog = "")
@IdClass(VoteChooseEntityPK.class)
public class VoteChooseEntity {
    private String studentId;
    private String studentGroupId;
    private int voteId;
    private String voteChoose;

    @Id
    @Column(name = "studentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "studentGroupID")
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "voteID")
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "voteChoose")
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
        return voteId == that.voteId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(voteChoose, that.voteChoose);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, voteId, voteChoose);
    }
}
