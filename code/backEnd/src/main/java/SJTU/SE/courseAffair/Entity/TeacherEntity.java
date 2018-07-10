package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Teacher", schema = "course_affair_DB")
@IdClass(TeacherEntityPK.class)
public class TeacherEntity {
    private String teacherId;
    private String teacherGroupId;

    @Id
    @Column(name = "teacherID", nullable = false, length = 28)
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Id
    @Column(name = "teacher_GroupID", nullable = false, length = 28)
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

        TeacherEntity that = (TeacherEntity) o;

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
