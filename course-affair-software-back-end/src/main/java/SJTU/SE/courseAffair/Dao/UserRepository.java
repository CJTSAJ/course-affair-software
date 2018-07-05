package SJTU.SE.courseAffair.Dao;

import SJTU.SE.courseAffair.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public List<UserEntity> getAllBy();

}
