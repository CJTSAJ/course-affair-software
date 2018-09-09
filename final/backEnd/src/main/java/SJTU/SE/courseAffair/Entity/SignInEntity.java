package SJTU.SE.courseAffair.Entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "Sign_In", schema = "course_affair_DB")
public class SignInEntity {
    private int signInId;
    private String signInGroupId;
    private Timestamp signDate;
    private String signCode;
    private double latitude;
    private double longitude;
    @Id
    @Column(name = "sign_InID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSignInId() {
        return signInId;
    }

    public void setSignInId(int signInId) {
        this.signInId = signInId;
    }

    @Basic
    @Column(name = "sign_In_GroupID", nullable = true, length = 29)
    public String getSignInGroupId() {
        return signInGroupId;
    }

    public void setSignInGroupId(String signInGroupId) {
        this.signInGroupId = signInGroupId;
    }
    
    @Basic
    @Column(name = "sign_date", nullable = true)
    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp SignDate) {
        this.signDate = SignDate;
    }
    
    @Basic
    @Column(name = "sign_code", nullable = true, length = 4)
    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }
    
    @Basic
    @Column(name = "latitude", nullable = true)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double Latitude) {
        this.latitude = Latitude;
    }
    
    @Basic
    @Column(name = "longitude", nullable = true)
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double Longitude) {
        this.longitude = Longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignInEntity that = (SignInEntity) o;

        if (signInId != that.signInId) return false;
        if (signInGroupId != null ? !signInGroupId.equals(that.signInGroupId) : that.signInGroupId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = signInId;
        result = 31 * result + (signInGroupId != null ? signInGroupId.hashCode() : 0);
        return result;
    }
}
