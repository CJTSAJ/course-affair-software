package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Teacher", schema = "wx2", catalog = "")
@IdClass(TeacherEntityPK.class)
public class TeacherEntity {
    private String teacherId;
    private String teacherGroupId;
    private String teacherName;

    @Id
    @Column(name = "teacherID")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Id
    @Column(name = "teacherGroupID")
    public String getTeacherGroupId() {
        return teacherGroupId;
    }

    public void setTeacherGroupId(String teacherGroupId) {
        this.teacherGroupId = teacherGroupId;
    }

    @Basic
    @Column(name = "teacherName")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherGroupId, that.teacherGroupId) &&
                Objects.equals(teacherName, that.teacherName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teacherId, teacherGroupId, teacherName);
    }
}
