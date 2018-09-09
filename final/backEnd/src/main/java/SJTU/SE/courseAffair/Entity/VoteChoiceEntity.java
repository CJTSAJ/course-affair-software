package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Vote_Choice", schema = "course_affair_DB")
@IdClass(VoteChoiceEntityPK.class)
public class VoteChoiceEntity {
    private int voteId;
    private int voteChoiceNo;
    private String voteChoiceContent;

    @Id
    @Column(name = "voteID", nullable = false)
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

    @Basic
    @Column(name = "vote_Choice_Content", nullable = true, length = 1024)
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

        if (voteId != that.voteId) return false;
        if (voteChoiceNo != that.voteChoiceNo) return false;
        if (voteChoiceContent != null ? !voteChoiceContent.equals(that.voteChoiceContent) : that.voteChoiceContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + voteChoiceNo;
        result = 31 * result + (voteChoiceContent != null ? voteChoiceContent.hashCode() : 0);
        return result;
    }
}
