package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    public List<QuestionEntity> findByTestId(int testId);
}