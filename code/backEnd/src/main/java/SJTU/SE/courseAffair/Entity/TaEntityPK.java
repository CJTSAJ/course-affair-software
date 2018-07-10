package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TaEntityPK implements Serializable {
    private String taid;
    private String taGroupId;

    @Column(name = "TAID", nullable = false, length = 28)
    @Id
    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    @Column(name = "TAGroupID", nullable = false, length = 28)
    @Id
    public String getTaGroupId() {
        return taGroupId;
    }

    public void setTaGroupId(String taGroupId) {
        this.taGroupId = taGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaEntityPK that = (TaEntityPK) o;

        if (taid != null ? !taid.equals(that.taid) : that.taid != null) return false;
        if (taGroupId != null ? !taGroupId.equals(that.taGroupId) : that.taGroupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taid != null ? taid.hashCode() : 0;
        result = 31 * result + (taGroupId != null ? taGroupId.hashCode() : 0);
        return result;
    }
}
