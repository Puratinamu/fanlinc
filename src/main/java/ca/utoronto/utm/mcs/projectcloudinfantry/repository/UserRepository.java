package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long>  {
    // Expect each user to have a unique email
    User findByEmail(String email);

    // Expect each user to have a unique email
    User findByUsername(String username);
}
