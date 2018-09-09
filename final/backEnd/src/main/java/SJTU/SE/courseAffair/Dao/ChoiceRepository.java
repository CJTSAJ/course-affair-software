package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Integer> {
    List<ChoiceEntity> findByTestIdAndQuestionId(int testId, int questionId);
}
