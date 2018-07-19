package SJTU.SE.courseAffair.Dao;
import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.StudentEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, StudentEntityPK> {
    List<StudentEntity> findByStudentGroupId(String studentGroupId);
    List<StudentEntity> findByStudentIdAndStudentGroupId(String studentId, String studentGroupId);
}
