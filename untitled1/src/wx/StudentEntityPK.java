package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;

    @Column(name = "studentID")
    @Id
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "studentGroupID")
    @Id
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntityPK that = (StudentEntityPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId);
    }
}
