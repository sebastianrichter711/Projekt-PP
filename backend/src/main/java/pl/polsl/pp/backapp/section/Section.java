package pl.polsl.pp.backapp.section;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.polsl.pp.backapp.topic.Topic;
import pl.polsl.pp.backapp.user.User;

import java.util.List;

@Document(collection="sections")
public class Section {

    @Id
    private String id;
    private String name;
    private Integer topicsNumber;
    private User moderator;

    @JsonIgnore
    private List<Topic> topics;

    private Section(String name, Integer topicsNumber, User moderator, List<Topic> topics){
        this.name = name;
        this.topicsNumber = topicsNumber;
        this.moderator = moderator;
        this.topics = topics;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Integer getTopicsNumber() {return topicsNumber;}
    public User getModerator() {return moderator;}
    public List<Topic> getTopics() {return topics;}

    public void setId(String newId) {id=newId;}
    public void setName(String newName) {name=newName;}
    public void setTopicsNumber(Integer newTopicsNumber) {topicsNumber=newTopicsNumber;}
    public void setModerator(User newModerator) {moderator=newModerator;}
    public void setTopics(List<Topic> newTopics) {topics=newTopics;}

}
