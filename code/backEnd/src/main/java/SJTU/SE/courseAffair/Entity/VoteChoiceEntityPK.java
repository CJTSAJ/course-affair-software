package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class VoteChoiceEntityPK implements Serializable {
    private int voteId;
    private String voteChoiceNo;

    @Column(name = "voteID", nullable = false)
    @Id
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Column(name = "voteChoiceNo", nullable = false, length = 1)
    @Id
    public String getVoteChoiceNo() {
        return voteChoiceNo;
    }

    public void setVoteChoiceNo(String voteChoiceNo) {
        this.voteChoiceNo = voteChoiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteChoiceEntityPK that = (VoteChoiceEntityPK) o;

        if (voteId != that.voteId) return false;
        if (voteChoiceNo != null ? !voteChoiceNo.equals(that.voteChoiceNo) : that.voteChoiceNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + (voteChoiceNo != null ? voteChoiceNo.hashCode() : 0);
        return result;
    }
}
