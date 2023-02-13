package ua.com.foxminded.university.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.model.LessonEntity;
import ua.com.foxminded.university.persistance.model.TeacherEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

  @Query(value = "select l from TeacherEntity t " +
          "join fetch LessonEntity l on l.teacher.id=t.id " +
          "where t.id = :id and date_trunc('day', l.startTime) = :date")
    List<LessonEntity> findAllLessonsForDayByTeacherIdAndDate(Long id, Date date);

  @Query(value = "select l from TeacherEntity t " +
          "join fetch LessonEntity l on l.teacher.id=t.id " +
          "where t.id = :id and date_trunc('month', l.startTime) = :date")
    List<LessonEntity> findAllLessonsForMonthByTeacherIdAndDate(Long id, Date date);
}
