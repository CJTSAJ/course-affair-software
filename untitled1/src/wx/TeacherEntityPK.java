package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeacherEntityPK implements Serializable {
    private String teacherId;
    private String teacherGroupId;

    @Column(name = "teacherID")
    @Id
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "teacherGroupID")
    @Id
    public String getTeacherGroupId() {
        return teacherGroupId;
    }

    public void setTeacherGroupId(String teacherGroupId) {
        this.teacherGroupId = teacherGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntityPK that = (TeacherEntityPK) o;
        return Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherGroupId, that.teacherGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teacherId, teacherGroupId);
    }
}
