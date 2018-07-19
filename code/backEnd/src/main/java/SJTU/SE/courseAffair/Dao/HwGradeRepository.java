package SJTU.SE.courseAffair.Dao;
import SJTU.SE.courseAffair.Entity.HwGradeEntity;
import SJTU.SE.courseAffair.Entity.HwGradeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HwGradeRepository extends JpaRepository<HwGradeEntity, HwGradeEntityPK> {
    List<HwGradeEntity> findByStudentIdAndStudentGroupIdAndHomeworkId(String studentid, String studentgroupid, int homeworkid);
}
