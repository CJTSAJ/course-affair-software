package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VoteChoiceEntityPK implements Serializable {
    private int voteId;
    private String voteChoiceNo;

    @Column(name = "voteID")
    @Id
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Column(name = "voteChoiceNo")
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
        return voteId == that.voteId &&
                Objects.equals(voteChoiceNo, that.voteChoiceNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(voteId, voteChoiceNo);
    }
}
