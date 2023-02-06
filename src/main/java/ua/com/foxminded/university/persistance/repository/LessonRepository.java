package ua.com.foxminded.university.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.model.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
