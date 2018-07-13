package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.HwGradeEntity;
import SJTU.SE.courseAffair.Entity.HwGradeEntityPK;

public interface HwGradeRepository extends JpaRepository<HwGradeEntity, HwGradeEntityPK>{
	List<HwGradeEntity> findByStudentIdAndStudentGroupIdAndHomeworkId(String studentid, String studentgroupid, int homeworkid);
}
