package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import SJTU.SE.courseAffair.Entity.StudentEntity;
import SJTU.SE.courseAffair.Entity.StudentEntityPK;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, StudentEntityPK> {
	public List<StudentEntity> findByStudentIdAndStudentGroupId(String studentId, String studentGroupId);
	public List<StudentEntity> findByStudentGroupId(String studentGroupId);
	
    @Transactional
	@Query(value = "delete from student where student.studentid=?1 and student.student_groupid=?2", nativeQuery = true)
	@Modifying
	public void deleteByStudentIdAndStudentGroupId(String studentId, String studentGroupId);
}
