package ua.com.foxminded.university.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.models.FacultyEntity;

import java.util.Optional;

@Repository
public interface FacultyRepo extends CrudRepository<FacultyEntity, Long> {
    Optional<FacultyEntity> findByName(String name);
}
