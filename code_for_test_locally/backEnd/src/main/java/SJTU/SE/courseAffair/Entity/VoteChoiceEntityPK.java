package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class VoteChoiceEntityPK implements Serializable {
    private int voteId;
    private int voteChoiceNo;

    @Column(name = "voteID", nullable = false)
    @Id
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Id
    @Column(name = "vote_Choice_No", nullable = false)
    public int getVoteChoiceNo() {
        return voteChoiceNo;
    }

    public void setVoteChoiceNo(int voteChoiceNo) {
        this.voteChoiceNo = voteChoiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteChoiceEntityPK that = (VoteChoiceEntityPK) o;

        if (voteId != that.voteId) return false;
        if (voteChoiceNo != that.voteChoiceNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + voteChoiceNo;
        return result;
    }
}
