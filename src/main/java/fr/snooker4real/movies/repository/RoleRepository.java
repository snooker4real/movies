package fr.snooker4real.movies.repository;

import fr.snooker4real.movies.model.ERole;
import fr.snooker4real.movies.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
