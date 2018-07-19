package SJTU.SE.courseAffair.Dao;
import SJTU.SE.courseAffair.Entity.TeacherEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<TeacherEntity, TeacherEntityPK>{
    List<TeacherEntity> findByTeacherIdAndTeacherGroupId(String teacherId, String teacherGroupId);
}
