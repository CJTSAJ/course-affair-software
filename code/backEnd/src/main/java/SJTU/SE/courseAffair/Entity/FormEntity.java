package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Form", schema = "course_affair_DB")
@IdClass(FormEntityPK.class)
public class FormEntity {
    private String sId;
    private String formId;

    @Id
    @Column(name = "sID", nullable = false, length = 28)
    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    @Id
    @Column(name = "formID", nullable = false, length = 100)
    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormEntity that = (FormEntity) o;
        return Objects.equals(sId, that.sId) &&
                Objects.equals(formId, that.formId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sId, formId);
    }
}
