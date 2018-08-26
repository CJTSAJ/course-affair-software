package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import SJTU.SE.courseAffair.Entity.TeacherEntity;
import SJTU.SE.courseAffair.Entity.TeacherEntityPK;

public interface TeacherRepository extends JpaRepository<TeacherEntity, TeacherEntityPK>{
	public List<TeacherEntity> findByTeacherIdAndTeacherGroupId(String teacherId, String teacherGroupId);
	public List<TeacherEntity> findByTeacherGroupId(String teacherGroupId);
	
	@Transactional
	@Query(value = "update teacher set teacher_name=?1 where teacherid=?2 and teacher_groupid=?3", nativeQuery = true)
	@Modifying
	public void updateTeacher(String teacher_name, String teacherid, String teacher_groupid);
}
