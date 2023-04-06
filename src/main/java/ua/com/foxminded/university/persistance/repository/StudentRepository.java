package ua.com.foxminded.university.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.model.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    StudentEntity findByEmail(String email);
}
