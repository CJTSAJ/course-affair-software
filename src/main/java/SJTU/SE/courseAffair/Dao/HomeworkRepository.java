package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.HomeworkEntity;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer>{
	public List<HomeworkEntity> findByHomeworkGroupId(String groupId);
}
