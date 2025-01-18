package in.himanshu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.himanshu.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
