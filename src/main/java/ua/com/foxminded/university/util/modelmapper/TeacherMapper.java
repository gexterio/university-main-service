package ua.com.foxminded.university.util.modelmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.persistance.model.TeacherEntity;

@Component
public class TeacherMapper {

    private final FacultyMapper facultyMapper;

    @Autowired
    public TeacherMapper(FacultyMapper facultyMapper) {
        this.facultyMapper = facultyMapper;
    }

    public TeacherEntity toEntity(TeacherDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("input couldn't be null");
        }
        return new TeacherEntity.Builder(dto.getFirstName(), dto.getLastName())
                .setId(dto.getId())
                .setAge(dto.getAge())
                .setEmail(dto.getEmail())
                .setFaculty(facultyMapper.toEntity(dto.getFaculty()))
                .setExperience(dto.getExperience())
                .setGrade(dto.getGrade())
                .build();
    }

    public TeacherDTO toDto(TeacherEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("input couldn't be null");
        }
        return new TeacherDTO.Builder()
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setId(entity.getId())
                .setAge(entity.getAge())
                .setEmail(entity.getEmail())
                .setExperience(entity.getExperience())
                .setGrade(entity.getGrade())
                .setFaculty(facultyMapper.toDto(entity.getFaculty()))
                .build();
    }
}
