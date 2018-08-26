package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import SJTU.SE.courseAffair.Entity.HomeworkEntity;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer>{
	public List<HomeworkEntity> findByHomeworkGroupId(String groupId);
	
	@Transactional
	@Query(value = "select * from homework where homework_groupid=?1 order by hwdate desc limit 3", nativeQuery = true)
	@Modifying
	public List<HomeworkEntity> findRecentHomework(String homework_groupid);
}
