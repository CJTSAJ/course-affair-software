package SJTU.SE.courseAffair.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SJTU.SE.courseAffair.Entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, String> {
	public List<FileEntity> findByOpengid(String opengid);
}
