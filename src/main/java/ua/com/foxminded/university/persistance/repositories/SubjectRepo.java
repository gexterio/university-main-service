package ua.com.foxminded.university.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.models.SubjectEntity;

@Repository
public interface SubjectRepo extends CrudRepository<SubjectEntity, Long> {
}
