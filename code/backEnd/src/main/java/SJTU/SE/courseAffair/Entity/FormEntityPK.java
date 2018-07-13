package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FormEntityPK implements Serializable {
    private String sId;
    private String formId;

    @Column(name = "sID", nullable = false, length = 28)
    @Id
    public String getSId() {
        return sId;
        }

    public void setSId(String sId) {
        this.sId = sId;
        }

    @Column(name = "formID", nullable = false, length = 100)
    @Id
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
        FormEntityPK that = (FormEntityPK) o;
        return Objects.equals(sId, that.sId) &&
                Objects.equals(formId, that.formId);
        }

    @Override
    public int hashCode() {
        return Objects.hash(sId, formId);
        }

}
