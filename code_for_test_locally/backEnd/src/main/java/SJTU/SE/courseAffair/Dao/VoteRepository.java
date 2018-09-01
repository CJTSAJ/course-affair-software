package SJTU.SE.courseAffair.Dao;


import SJTU.SE.courseAffair.Entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {
    List<VoteEntity> findByVoteGroupId(String groupId);
    List<VoteEntity> findByVoteId(int voteId);
}
