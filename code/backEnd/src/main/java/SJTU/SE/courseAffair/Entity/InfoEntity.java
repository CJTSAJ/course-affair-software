package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "info", schema = "course_affair_db")
@IdClass(InfoEntityPK.class)

public class InfoEntity {
    private String infoGroupId;
    private String infoStuId;
    private String infoStuName;

    @Id
    @Column(name = "infoGroupID", nullable = false, length = 29)
    public String getInfoGroupId() {
        return infoGroupId;
    }

    public void setInfoGroupId(String infoGroupId) {
        this.infoGroupId = infoGroupId;
    }


    @Id
    @Column(name = "infoStuID", nullable = false, length = 28)
    public String getInfoStuId() {
        return infoStuId;
    }

    public void setInfoStuId(String infoStuId) {
        this.infoStuId = infoStuId;
    }


    @Basic
    @Column(name = "infoStuName", nullable = false, length = 30)
    public String getInfoStuName() {
        return infoStuName;
    }

    public void setInfoStuName(String infoStuName) {
        this.infoStuName = infoStuName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoEntity that = (InfoEntity) o;
        return Objects.equals(infoGroupId, that.infoGroupId) &&
                Objects.equals(infoStuId, that.infoStuId) &&
                Objects.equals(infoStuName, that.infoStuName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(infoGroupId, infoStuId, infoStuName);
    }
}
