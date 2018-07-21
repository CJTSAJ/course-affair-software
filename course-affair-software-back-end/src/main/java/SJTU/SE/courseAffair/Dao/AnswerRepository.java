package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    List<AnswerEntity> findByTestIdAndStudentGroupIdAndStudentId(int testId, String openGId, String openId);
    AnswerEntity findByTestIdAndStudentGroupIdAndStudentIdAndQuestionId(int testId, String openGId, String openId, int questionId);
}
