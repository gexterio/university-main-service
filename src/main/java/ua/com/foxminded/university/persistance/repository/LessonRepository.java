package ua.com.foxminded.university.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.model.LessonEntity;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    @Query(value = "select l from StudentEntity s " +
            "join GroupEntity g on s.group.id = g.id " +
            "join LessonEntity l on g.id = l.group.id " +
            "where s.id = :id " +
            "and l.startTime between :from and :to")
    List<LessonEntity> findAllLessonsByStudentIdAndDate(Long id, ZonedDateTime from, ZonedDateTime to);

    @Query(value = "select l from TeacherEntity t " +
            "join fetch LessonEntity l on l.teacher.id=t.id " +
            "where t.id = :id " +
            "and l.startTime between :from and :to")
    List<LessonEntity> findAllLessonsByTeacherIdAndDate(Long id, ZonedDateTime from, ZonedDateTime to);
}
