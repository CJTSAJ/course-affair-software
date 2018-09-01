package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.VoteChooseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteChooseRepository extends JpaRepository<VoteChooseEntity, Integer>{
    VoteChooseEntity findByVoteIdAndStudentGroupIdAndStudentId(int voteId, String groupId, String studentId);
}
