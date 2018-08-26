package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import SJTU.SE.courseAffair.Entity.TaEntity;
import SJTU.SE.courseAffair.Entity.TaEntityPK;

public interface TaRepository extends JpaRepository<TaEntity, TaEntityPK> {
	public List<TaEntity> findByTaidAndTaGroupId(String taid, String taGroupId);
	public List<TaEntity> findByTaGroupId(String taGroupId);
	
	@Transactional
	@Query(value = "delete from ta where ta.taid=?1 and ta.tagroupid=?2", nativeQuery = true)
	@Modifying
	public void deleteByTaidAndTaGroupId(String taid, String taGroupId);
	
	@Transactional
	@Query(value = "update ta set taName=?1 where taid=?2 and tagroupid=?3", nativeQuery = true)
	@Modifying
	public void updateTa(String taName, String taid, String tagroupid);
}
