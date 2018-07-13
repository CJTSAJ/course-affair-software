package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TA", schema = "wx2", catalog = "")
@IdClass(TaEntityPK.class)
public class TaEntity {
    private String taid;
    private String taGroupId;
    private String taName;

    @Id
    @Column(name = "TAID")
    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    @Id
    @Column(name = "TAGroupID")
    public String getTaGroupId() {
        return taGroupId;
    }

    public void setTaGroupId(String taGroupId) {
        this.taGroupId = taGroupId;
    }

    @Basic
    @Column(name = "TAName")
    public String getTaName() {
        return taName;
    }

    public void setTaName(String taName) {
        this.taName = taName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaEntity taEntity = (TaEntity) o;
        return Objects.equals(taid, taEntity.taid) &&
                Objects.equals(taGroupId, taEntity.taGroupId) &&
                Objects.equals(taName, taEntity.taName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taid, taGroupId, taName);
    }
}
