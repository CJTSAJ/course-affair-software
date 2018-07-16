package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "form", schema = "course_affair_db")
@IdClass(FormEntityPK.class)
public class FormEntity {
    private String stuId;
    private String formId;

    @Id
    @Column(name = "stuID", nullable = false, length = 28)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

        if (stuId != null ? !stuId.equals(that.stuId) : that.stuId != null) return false;
        if (formId != null ? !formId.equals(that.formId) : that.formId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId != null ? stuId.hashCode() : 0;
        result = 31 * result + (formId != null ? formId.hashCode() : 0);
        return result;
    }
}
