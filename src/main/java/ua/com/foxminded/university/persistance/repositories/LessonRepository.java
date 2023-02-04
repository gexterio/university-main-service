package ua.com.foxminded.university.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.models.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
