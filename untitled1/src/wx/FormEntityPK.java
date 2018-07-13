package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FormEntityPK implements Serializable {
    private String sId;
    private String formId;

    @Column(name = "sID")
    @Id
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Column(name = "formID")
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
