package wx;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SignInRecordEntityPK implements Serializable {
    private String studentId;
    private String studentGroupId;
    private int signInId;

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

    @Column(name = "signInID")
    @Id
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInRecordEntityPK that = (SignInRecordEntityPK) o;
        return signInId == that.signInId &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentGroupId, that.studentGroupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentGroupId, signInId);
    }
}
