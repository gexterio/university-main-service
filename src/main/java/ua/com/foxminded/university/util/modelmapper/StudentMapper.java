package ua.com.foxminded.university.util.modelmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.persistance.model.StudentEntity;

@Component
public class StudentMapper {

    private final GroupMapper groupMapper;

    @Autowired
    public StudentMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public StudentEntity toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        return new StudentEntity.Builder(dto.getFirstName(), dto.getLastName())
                .setId(dto.getId())
                .setAge(dto.getAge())
                .setEmail(dto.getEmail())
                .setGroup(groupMapper.toEntity(dto.getGroup()))
                .setCourse(dto.getCourse())
                .build();
    }

    public StudentDTO toDto(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new StudentDTO.Builder()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setAge(entity.getAge())
                .setEmail(entity.getEmail())
                .setCourse(entity.getCourse())
                .setGroup(groupMapper.toDto(entity.getGroup()))
                .build();
    }
}
