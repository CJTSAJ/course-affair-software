package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "signin", schema = "course_affair_db")
public class SignInEntity {
    private int signInId;
    private String signInGroupId;

    @Id
    @Column(name = "signInID", nullable = false)
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    @Basic
    @Column(name = "signInGroupID", nullable = true, length = 29)
    public String getSignInGroupId() {
        return signInGroupId;
    }

    public void setSignInGroupId(String signInGroupId) {
        this.signInGroupId = signInGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignInEntity that = (SignInEntity) o;

        if (signInId != that.signInId) return false;
        if (signInGroupId != null ? !signInGroupId.equals(that.signInGroupId) : that.signInGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = signInId;
        result = 31 * result + (signInGroupId != null ? signInGroupId.hashCode() : 0);
        return result;
    }
}
