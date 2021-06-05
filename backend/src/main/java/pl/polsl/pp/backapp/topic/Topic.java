package pl.polsl.pp.backapp.topic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.polsl.pp.backapp.post.Post;
import pl.polsl.pp.backapp.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="topics")
public class Topic {

    @Id
    private String id;
    private String title;
    private User author;
    private Date createDate;
    private Date lastChange;
    private String description;

    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

//    public Topic(String title, User author, Date createDate, Date lastChange, String description){
//        this.title = title;
//        this.author = author;
//        this.createDate = createDate;
//        this.lastChange = lastChange;
//        this.description=description;
//    }

    public Topic(String title, User author, Date createDate, Date lastChange, String description, List<Post> posts){
        this.title = title;
        this.author = author;
        this.createDate = createDate;
        this.lastChange = lastChange;
        this.description=description;
        this.posts=posts;
    }

    public String getId() {return id;}
    public String getTitle() {return title;}
    public User getAuthor() {return author;}
    public Date getCreateDate() {return createDate;}
    public Date getLastChange() {return lastChange;}
    public String getDescription() {return description;}
    public List<Post> getPosts() {return posts;}

    public void setId(String newId) {id=newId;}
    public void setTitle(String newTitle) {title=newTitle;}
    public void setAuthor(User newAuthor) {author=newAuthor;}
    public void setCreateDate(Date newCreateDate) {createDate = newCreateDate;}
    public void setLastChange(Date newLastChange) {lastChange = newLastChange;}
    public void setDescription(String newDescription) {description = newDescription;}
    public void getPosts(List<Post> newPosts) {posts=newPosts;}
}
