package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ta", schema = "course_affair_db")
@IdClass(TaEntityPK.class)
public class TaEntity {
    private String taid;
    private String taGroupId;
    private String taName;

    @Id
    @Column(name = "TAID", nullable = false, length = 28)
    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    @Id
    @Column(name = "TAGroupID", nullable = false, length = 29)
    public String getTaGroupId() {
        return taGroupId;
    }

    public void setTaGroupId(String taGroupId) {
        this.taGroupId = taGroupId;
    }

    @Basic
    @Column(name = "TAName", nullable = true, length = 100)
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

        if (taid != null ? !taid.equals(taEntity.taid) : taEntity.taid != null) return false;
        if (taGroupId != null ? !taGroupId.equals(taEntity.taGroupId) : taEntity.taGroupId != null) return false;
        if (taName != null ? !taName.equals(taEntity.taName) : taEntity.taName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taid != null ? taid.hashCode() : 0;
        result = 31 * result + (taGroupId != null ? taGroupId.hashCode() : 0);
        result = 31 * result + (taName != null ? taName.hashCode() : 0);
        return result;
    }
}
