package pl.polsl.pp.backapp.topic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.backapp.user.User;

@Repository
public interface TopicRepository extends MongoRepository<Topic,String> {
}
