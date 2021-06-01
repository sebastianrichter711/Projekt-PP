package pl.polsl.pp.backapp.section;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.pp.backapp.topic.Topic;

@Repository
public interface SectionRepository extends MongoRepository<Section,String> {
}
