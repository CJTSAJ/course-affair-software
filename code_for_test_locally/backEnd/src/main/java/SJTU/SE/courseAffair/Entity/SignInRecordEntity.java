package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Sign_In_Record", schema = "course_affair_DB")
@IdClass(SignInRecordEntityPK.class)
public class SignInRecordEntity {
    private String studentId;
    private String studentGroupId;
    private int signInId;
    //private Byte present;

    @Id
    @Column(name = "studentID", nullable = false, length = 28)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "student_GroupID", nullable = false, length = 29)
    public String getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(String studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    @Id
    @Column(name = "sign_InID", nullable = false)
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    /*@Basic
    @Column(name = "present", nullable = true)
    public Byte getPresent() {
        return present;
    }

    public void setPresent(Byte present) {
        this.present = present;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignInRecordEntity that = (SignInRecordEntity) o;

        if (signInId != that.signInId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentGroupId != null ? !studentGroupId.equals(that.studentGroupId) : that.studentGroupId != null)
            return false;
        //if (present != null ? !present.equals(that.present) : that.present != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (studentGroupId != null ? studentGroupId.hashCode() : 0);
        result = 31 * result + signInId;
        //result = 31 * result + (present != null ? present.hashCode() : 0);
        return result;
    }
}
