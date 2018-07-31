package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.CorrectAnswerEntity;
import SJTU.SE.courseAffair.Entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswerEntity, Integer>{
    CorrectAnswerEntity findByTestIdAndQuestionId(int testId, int questionId);
}
