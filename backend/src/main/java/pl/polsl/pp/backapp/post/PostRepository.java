package pl.polsl.pp.backapp.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.backapp.topic.Topic;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
}
