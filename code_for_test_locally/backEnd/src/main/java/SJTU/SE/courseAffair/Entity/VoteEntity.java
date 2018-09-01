package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Vote", schema = "course_affair_DB")
public class VoteEntity {
    private int voteId;
    private String voteGroupId;
    private String voteTitle;
    private String voteContent;
    private Timestamp startTime;
    private Timestamp endTime;

    @Id
    @Column(name = "voteID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "vote_GroupID", nullable = true, length = 29)
    public String getVoteGroupId() {
        return voteGroupId;
    }

    public void setVoteGroupId(String voteGroupId) {
        this.voteGroupId = voteGroupId;
    }

    @Basic
    @Column(name = "vote_Content", nullable = true, length = 1024)
    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    @Basic
    @Column(name = "vote_Title", nullable = true, length = 12)
    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    @Basic
    @Column(name = "vote_StartTime", nullable = true)
    public Timestamp getStartTime() {return startTime;}

    public void setStartTime(Timestamp startTime) {this.startTime = startTime;}


    @Basic
    @Column(name = "vote_EndTime", nullable = true)
    public Timestamp getEndTime() {return endTime;}

    public void setEndTime(Timestamp endTime) {this.endTime = endTime;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteEntity that = (VoteEntity) o;

        if (voteId != that.voteId) return false;
        if (voteGroupId != null ? !voteGroupId.equals(that.voteGroupId) : that.voteGroupId != null) return false;
        if (voteContent != null ? !voteContent.equals(that.voteContent) : that.voteContent != null) return false;
        if (endTime != that.endTime) return false;
        if (startTime != that.startTime) return false;
        if (voteTitle != null ? !voteTitle.equals(that.voteTitle): that.voteTitle != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + (voteGroupId != null ? voteGroupId.hashCode() : 0);
        result = 31 * result + (voteContent != null ? voteContent.hashCode() : 0);
        result = 31 * result + (voteTitle != null ? voteTitle.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
