package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Vote", schema = "wx2", catalog = "")
public class VoteEntity {
    private int voteId;
    private String voteGroupId;
    private String voteContent;

    @Id
    @Column(name = "voteID")
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "voteGroupID")
    public String getVoteGroupId() {
        return voteGroupId;
    }

    public void setVoteGroupId(String voteGroupId) {
        this.voteGroupId = voteGroupId;
    }

    @Basic
    @Column(name = "voteContent")
    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntity that = (VoteEntity) o;
        return voteId == that.voteId &&
                Objects.equals(voteGroupId, that.voteGroupId) &&
                Objects.equals(voteContent, that.voteContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(voteId, voteGroupId, voteContent);
    }
}
