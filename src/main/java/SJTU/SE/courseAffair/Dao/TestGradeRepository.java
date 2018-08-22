package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.TestGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestGradeRepository extends JpaRepository<TestGradeEntity, Integer>{
    TestGradeEntity findByTestIdAndStudentGroupIdAndStudentId(int testId, String openGId, String openId);
}
