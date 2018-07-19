package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.TeacherEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntityPK;

public interface TeacherRepository extends JpaRepository<TeacherEntity, TeacherEntityPK>{
	public List<TeacherEntity> findByTeacherIdAndTeacherGroupId(String teacherId, String teacherGroupId);
	public List<TeacherEntity> findByTeacherGroupId(String teacherGroupId);
}
