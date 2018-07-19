package SJTU.SE.courseAffair.Dao;
import SJTU.SE.courseAffair.Entity.TaEntity;
import SJTU.SE.courseAffair.Entity.TaEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaRepository extends JpaRepository<TaEntity, TaEntityPK> {
    List<TaEntity> findByTaidAndTaGroupId(String taid,String taGroupId);
}
