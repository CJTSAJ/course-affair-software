package SJTU.SE.courseAffair.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SJTU.SE.courseAffair.Entity.SignInEntity;

public interface SignRepository  extends JpaRepository<SignInEntity, Integer> {
	//public List<SignInEntity> findBy
	//@Transactional
	@Query(value = "select * from sign_in as s1 where s1.sign_in_groupid=?1 and s1.sign_date=(select MAX(s2.sign_date) from sign_in as s2 where s2.sign_in_groupid=?1)", nativeQuery = true)
	public SignInEntity findRecentSignByGoupid(String groupid);
	
}
