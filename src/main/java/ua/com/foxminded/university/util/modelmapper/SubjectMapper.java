package ua.com.foxminded.university.util.modelmapper;

import org.springframework.stereotype.Component;
import ua.com.foxminded.university.consumer.dto.SubjectDTO;
import ua.com.foxminded.university.persistance.model.SubjectEntity;


@Component
public class SubjectMapper {

    public SubjectEntity toEntity(SubjectDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("input couldn't be null");
        }
        return new SubjectEntity.Builder(dto.getName())
                .setId(dto.getId())
                .setDescription(dto.getDescription())
                .build();
    }

    public SubjectDTO toDto(SubjectEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("input couldn't be null");
        }
        return new SubjectDTO.Builder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .build();
    }
}
