package ua.com.foxminded.university.util.modelmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.LessonDTO;
import ua.com.foxminded.university.persistance.model.LessonEntity;

@Component
public class LessonMapper {

    private final GroupMapper groupMapper;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public LessonMapper(GroupMapper groupMapper, SubjectMapper subjectMapper, TeacherMapper teacherMapper) {
        this.groupMapper = groupMapper;
        this.subjectMapper = subjectMapper;
        this.teacherMapper = teacherMapper;
    }

    public LessonEntity toEntity(LessonDTO dto) {
        if (dto == null) {
            return null;
        }
        return new LessonEntity.Builder(dto.getName())
                .setId(dto.getId())
                .setDuration(dto.getDuration())
                .setStartTime(dto.getStartTime())
                .setGroup(groupMapper.toEntity(dto.getGroup()))
                .setSubject(subjectMapper.toEntity(dto.getSubject()))
                .setTeacher(teacherMapper.toEntity(dto.getTeacher()))
                .build();
    }

    public LessonDTO toDto(LessonEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LessonDTO.Builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDuration(entity.getDuration())
                .setGroup(groupMapper.toDto(entity.getGroup()))
                .setStartTime(entity.getStartTime())
                .setSubject(subjectMapper.toDto(entity.getSubject()))
                .setTeacher(teacherMapper.toDto(entity.getTeacher()))
                .build();
    }
}
