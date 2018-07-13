package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VoteChoice", schema = "wx2", catalog = "")
@IdClass(VoteChoiceEntityPK.class)
public class VoteChoiceEntity {
    private int voteId;
    private String voteChoiceNo;
    private String voteChoiceContent;

    @Id
    @Column(name = "voteID")
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Id
    @Column(name = "voteChoiceNo")
    public String getVoteChoiceNo() {
        return voteChoiceNo;
    }

    public void setVoteChoiceNo(String voteChoiceNo) {
        this.voteChoiceNo = voteChoiceNo;
    }

    @Basic
    @Column(name = "voteChoiceContent")
    public String getVoteChoiceContent() {
        return voteChoiceContent;
    }

    public void setVoteChoiceContent(String voteChoiceContent) {
        this.voteChoiceContent = voteChoiceContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteChoiceEntity that = (VoteChoiceEntity) o;
        return voteId == that.voteId &&
                Objects.equals(voteChoiceNo, that.voteChoiceNo) &&
                Objects.equals(voteChoiceContent, that.voteChoiceContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(voteId, voteChoiceNo, voteChoiceContent);
    }
}
