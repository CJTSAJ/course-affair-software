package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "vote", schema = "course_affair_db")
public class VoteEntity {
    private int voteId;
    private String voteGroupId;
    private String voteContent;

    @Id
    @Column(name = "voteID", nullable = false)
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "voteGroupID", nullable = true, length = 29)
    public String getVoteGroupId() {
        return voteGroupId;
    }

    public void setVoteGroupId(String voteGroupId) {
        this.voteGroupId = voteGroupId;
    }

    @Basic
    @Column(name = "voteContent", nullable = true, length = 1024)
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

        if (voteId != that.voteId) return false;
        if (voteGroupId != null ? !voteGroupId.equals(that.voteGroupId) : that.voteGroupId != null) return false;
        if (voteContent != null ? !voteContent.equals(that.voteContent) : that.voteContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + (voteGroupId != null ? voteGroupId.hashCode() : 0);
        result = 31 * result + (voteContent != null ? voteContent.hashCode() : 0);
        return result;
    }
}
