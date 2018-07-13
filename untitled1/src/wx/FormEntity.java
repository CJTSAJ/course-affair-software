package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Form", schema = "wx2", catalog = "")
@IdClass(FormEntityPK.class)
public class FormEntity {
    private String sId;
    private String formId;

    @Id
    @Column(name = "sID")
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Id
    @Column(name = "formID")
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
