package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Student", schema = "wx2", catalog = "")
@IdClass(StudentEntityPK.class)
public class StudentEntity {
    private String studentId;
    private String studentGroupId;
    private String sno;
    private String sname;

    @Id
    @Column(name = "studentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "studentGroupID")
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Basic
    @Column(name = "sno")
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Basic
    @Column(name = "sname")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(sno, that.sno) &&
                Objects.equals(sname, that.sname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, sno, sname);
    }
}
