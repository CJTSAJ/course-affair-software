package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class InfoEntityPK implements Serializable {
    private String infoGroupId;
    private String infoStuId;

    @Column(name = "infoGroupID", nullable = false, length = 29)
    @Id
    public String getInfoGroupId() {
        return infoGroupId;
    }

    public void setInfoGroupId(String infoGroupId) {
        this.infoGroupId = infoGroupId;
    }


    @Column(name = "infoStuID", nullable = false, length = 28)
    @Id
    public String getInfoStuId() {
        return infoStuId;
    }

    public void setInfoStuId(String infoStuId) {
        this.infoStuId = infoStuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoEntityPK that = (InfoEntityPK) o;
        return Objects.equals(infoGroupId, that.infoGroupId) &&
                Objects.equals(infoStuId, that.infoStuId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(infoGroupId, infoStuId);
    }
}
