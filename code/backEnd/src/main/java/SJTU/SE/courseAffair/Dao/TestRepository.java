package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {
    public List<TestEntity> findByTestGroupId(String groupId);
    public TestEntity findByTestGroupIdAndStartTimeAndEndTimeAndTestContent(String testGroupId, Timestamp startTime, Timestamp endTime, String testContent);
}
