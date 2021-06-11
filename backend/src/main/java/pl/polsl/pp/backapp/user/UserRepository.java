package pl.polsl.pp.backapp.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}
