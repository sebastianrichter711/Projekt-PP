package pl.polsl.pp.backapp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;
import pl.polsl.pp.backapp.exception.ItemExistsInDatabaseException;
import pl.polsl.pp.backapp.user.User;
import pl.polsl.pp.backapp.user.UserService;

@RestController
public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topic")
    public Iterable<Topic> getTopics(){
        return topicService.getTopics();
    }

    @GetMapping("/topic/{id}")
    public Topic getTopic(@PathVariable String id) {
        try {
            return topicService.getTopic(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/topic")
    public Topic addTopic(@RequestBody Topic topic) {
        try {
            return topicService.addTopic(topic);
        } catch (ItemExistsInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (RuntimeException  e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/topic/{id}")
    public Topic updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        try {
            return topicService.updateTopic(id, topic);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ItemExistsInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/topic/{id}")
    public void deleteTopic(@PathVariable String id) {
        try {
            topicService.deleteTopic(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
