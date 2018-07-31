package SJTU.SE.courseAffair.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TeacherEntityPK implements Serializable {
    private String teacherId;
    private String teacherGroupId;

    @Column(name = "teacherID", nullable = false, length = 28)
    @Id
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "teacherGroupID", nullable = false, length = 29)
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

        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (teacherGroupId != null ? !teacherGroupId.equals(that.teacherGroupId) : that.teacherGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherId != null ? teacherId.hashCode() : 0;
        result = 31 * result + (teacherGroupId != null ? teacherGroupId.hashCode() : 0);
        return result;
    }
}
