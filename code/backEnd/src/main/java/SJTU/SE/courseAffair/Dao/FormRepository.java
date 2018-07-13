package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.FormEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<FormEntity, FormEntityPK> {
    List<FormEntity> findBySId(String sId);
}
