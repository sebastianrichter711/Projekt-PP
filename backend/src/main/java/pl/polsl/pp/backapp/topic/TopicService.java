package pl.polsl.pp.backapp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;

import java.util.Optional;


@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Iterable<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopic(String id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Topic of id " + id + " not found"));

        return topic;
    }

    public Topic addTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic updateTopic(String id, Topic updatedTopic) {
        Optional<Topic> topic = topicRepository.findById(id);

        if(topic.isEmpty())
            throw new IdNotFoundInDatabaseException("Topic of id " + id + " not found");

        return topicRepository.save(updatedTopic);
    }

    public void deleteTopic(String id) {
        try {
            topicRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
