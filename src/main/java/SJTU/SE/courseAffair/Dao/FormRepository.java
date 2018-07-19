package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.FormEntity;
import SJTU.SE.courseAffair.Entity.FormEntityPK;

public interface FormRepository extends JpaRepository<FormEntity, FormEntityPK> {
    List<FormEntity> findByStuId(String sId);
}
