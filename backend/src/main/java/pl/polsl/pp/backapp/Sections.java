package pl.polsl.pp.backapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection="sections")
public class Sections {

    @Id
    private String id;
    private String name;
    private Integer topicsNumber;
    private Users moderator;
    private List<Topics> topics;

    private Sections(String name, Integer topicsNumber, Users moderator, List<Topics> topics){
        this.name = name;
        this.topicsNumber = topicsNumber;
        this.moderator = moderator;
        this.topics = topics;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Integer getTopicsNumber() {return topicsNumber;}
    public Users getModerator() {return moderator;}
    public List<Topics> getTopics() {return topics;}

    public void setId(String newId) {id=newId;}
    public void setName(String newName) {name=newName;}
    public void setTopicsNumber(Integer newTopicsNumber) {topicsNumber=newTopicsNumber;}
    public void setModerator(Users newModerator) {moderator=newModerator;}
    public void setTopics(List<Topics> newTopics) {topics=newTopics;}

}
