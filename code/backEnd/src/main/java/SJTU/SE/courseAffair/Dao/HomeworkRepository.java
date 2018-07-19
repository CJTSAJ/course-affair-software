package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer>{
    public List<HomeworkEntity> findByHomeworkGroupId(String groupId);
}
