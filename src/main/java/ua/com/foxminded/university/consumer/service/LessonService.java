package ua.com.foxminded.university.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.persistance.repository.LessonRepository;
import ua.com.foxminded.university.util.modelmapper.LessonMapper;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository repository;
    private final LessonMapper mapper;

    @Autowired
    public LessonService(LessonRepository repository, LessonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<LessonDTO> findLessonsForStudentForDay(Long id, ZonedDateTime date) {
        ZonedDateTime from = date.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        return repository.findAllLessonsByStudentIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForStudentForMonth(Long id, ZonedDateTime date) {
        ZonedDateTime from = date.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
        ZonedDateTime to = from.plusMonths(1);
        return repository.findAllLessonsByStudentIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForTeacherForDay(Long id, ZonedDateTime date) {
        ZonedDateTime from = date.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime to = from.plusDays(1);
        return repository.findAllLessonsByTeacherIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LessonDTO> findLessonsForTeacherForMonth(Long id, ZonedDateTime date) {
        ZonedDateTime from = date.truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1);
        ZonedDateTime to = from.plusMonths(1);
        return repository.findAllLessonsByTeacherIdAndDate(id, from, to).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
