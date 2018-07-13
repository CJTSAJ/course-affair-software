package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TaEntityPK implements Serializable {
    private String taid;
    private String taGroupId;

    @Column(name = "TAID")
    @Id
    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    @Column(name = "TAGroupID")
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
        return Objects.equals(taid, that.taid) &&
                Objects.equals(taGroupId, that.taGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taid, taGroupId);
    }
}
