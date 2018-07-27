package SJTU.SE.courseAffair.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import SJTU.SE.courseAffair.Entity.InfoEntity;
import SJTU.SE.courseAffair.Entity.InfoEntityPK;


public interface InfoRepository extends JpaRepository<InfoEntity, InfoEntityPK>  {
    List<InfoEntity> findByInfoGroupIdAndInfoStuIdAndInfoStuName(String infoGroupId, String infoStuId, String infoStuName);
}
