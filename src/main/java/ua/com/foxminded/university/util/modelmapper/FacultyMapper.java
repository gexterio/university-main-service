package ua.com.foxminded.university.util.modelmapper;

import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.FacultyDTO;
import ua.com.foxminded.university.persistance.model.FacultyEntity;

@Component
public class FacultyMapper {

    public FacultyEntity toEntity(FacultyDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FacultyEntity.Builder(dto.getName())
                .setId(dto.getId())
                .setDuration(dto.getDuration())
                .build();
    }

    public FacultyDTO toDto(FacultyEntity entity) {
        if (entity == null) {
            return null;
        }
        return new FacultyDTO.Builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDuration(entity.getDuration())
                .build();
    }
}
