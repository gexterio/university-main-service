package ua.com.foxminded.university.persistance.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.university.persistance.models.GroupEntity;

import java.util.Optional;

@Repository
public interface GroupRepo extends CrudRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByName(String name);

}
