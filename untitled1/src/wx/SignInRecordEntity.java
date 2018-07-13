package wx;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SignInRecord", schema = "wx2", catalog = "")
@IdClass(SignInRecordEntityPK.class)
public class SignInRecordEntity {
    private String studentId;
    private String studentGroupId;
    private int signInId;
    private Byte present;

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

    @Id
    @Column(name = "signInID")
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    @Basic
    @Column(name = "present")
    public Byte getPresent() {
        return present;
    }

    public void setPresent(Byte present) {
        this.present = present;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInRecordEntity that = (SignInRecordEntity) o;
        return signInId == that.signInId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(present, that.present);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, signInId, present);
    }
}
