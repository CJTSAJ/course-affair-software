package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SignIn", schema = "wx2", catalog = "")
public class SignInEntity {
    private int signInId;
    private String signInGroupId;

    @Id
    @Column(name = "signInID")
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    @Basic
    @Column(name = "signInGroupID")
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
        return signInId == that.signInId &&
                Objects.equals(signInGroupId, that.signInGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(signInId, signInGroupId);
    }
}
