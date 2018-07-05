package SJTU.SE.courseAffair.Entity;

import javax.persistence.*;

@Entity
@Table(name = "User", schema = "test", catalog = "")
public class UserEntity{
    private int id_user;
    private String psw;

    @Id
    @Column(name = "id_user", nullable = false)
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Basic
    @Column(name = "psw", nullable = false, length = 45)
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id_user != that.id_user) return false;
        if (psw != null ? !psw.equals(that.psw) : that.psw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_user;
        result = 31 * result + (psw != null ? psw.hashCode() : 0);
        return result;
    }
}
