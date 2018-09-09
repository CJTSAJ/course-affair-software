package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.SignInRecordEntity;
import SJTU.SE.courseAffair.Entity.SignInRecordEntityPK;

public interface SignInRecordRepository extends JpaRepository<SignInRecordEntity, SignInRecordEntityPK> {
	public List<SignInRecordEntity> findByStudentIdAndStudentGroupIdAndSignInId(String studentId, String studentGroupId, int signInId);
	public List<SignInRecordEntity> findBySignInId(int signInId);
}
