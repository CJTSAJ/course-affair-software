package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.VoteChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteChoiceRepository extends JpaRepository<VoteChoiceEntity, Integer> {
    List<VoteChoiceEntity> findByVoteId(int voteId);
}
