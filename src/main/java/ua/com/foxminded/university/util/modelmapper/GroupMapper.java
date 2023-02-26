package ua.com.foxminded.university.util.modelmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.GroupDTO;
import ua.com.foxminded.university.persistance.model.GroupEntity;

@Component
public class GroupMapper {

    private final FacultyMapper facultyMapper;

    @Autowired
    public GroupMapper(FacultyMapper facultyMapper) {
        this.facultyMapper = facultyMapper;
    }

    public GroupEntity toEntity(GroupDTO dto) {
        if (dto == null) {
            return null;
        }
        return new GroupEntity.Builder(dto.getName())
                .setId(dto.getId())
                .setFaculty(facultyMapper.toEntity(dto.getFaculty()))
                .build();
    }

    public GroupDTO toDto(GroupEntity entity) {
        if (entity == null) {
            return null;
        }
        return new GroupDTO.Builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setFaculty(facultyMapper.toDto(entity.getFaculty()))
                .build();
    }
}
