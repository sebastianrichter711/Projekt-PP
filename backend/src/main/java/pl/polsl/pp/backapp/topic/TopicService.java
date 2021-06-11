package pl.polsl.pp.backapp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.auth.UserPrincipal;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;
import pl.polsl.pp.backapp.user.User;
import pl.polsl.pp.backapp.user.UserRepository;

import java.util.Date;


@Service
public class TopicService {

    private TopicRepository topicRepository;
    private UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
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
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userPrincipal.getUsername()).
                orElseThrow(() -> new IdNotFoundInDatabaseException("Can't find currently logged in user " +
                        userPrincipal.getUsername() + " in database!"));

        topic.setCreateDate(new Date());
        topic.setLastChange(new Date());
        topic.setAuthor(user);

        user.incPostsNumber();
        userRepository.save(user);
        return topicRepository.save(topic);
    }

    public Topic updateTopic(String id, Topic updatedTopic) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Topic of id " + id + " not found"));

        topic.setLastChange(new Date());
        topic.setDescription(updatedTopic.getDescription());
        topic.setTitle(updatedTopic.getTitle());

        return topicRepository.save(updatedTopic);
    }

    public void deleteTopic(String id) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByLogin(userPrincipal.getUsername()).
                    orElseThrow(() -> new IdNotFoundInDatabaseException("Can't find currently logged in user " +
                        userPrincipal.getUsername() + " in database!"));

            user.decPostsNumber();
            userRepository.save(user);

            topicRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
