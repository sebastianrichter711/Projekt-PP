package pl.polsl.pp.backapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection="topics")
public class Topics {

    @Id
    private String id;
    private String title;
    private Users author;
    private Date createDate;
    private Date lastChange;
    private String description;
    private List<Posts> posts;

    public Topics(String title, Users author, Date createDate, Date lastChange, String description, List<Posts> posts){
        this.title = title;
        this.author = author;
        this.createDate = createDate;
        this.lastChange = lastChange;
        this.description=description;
        this.posts=posts;
    }

    public String getId() {return id;}
    public String getTitle() {return title;}
    public Users getAuthor() {return author;}
    public Date getCreateDate() {return createDate;}
    public Date getLastChange() {return lastChange;}
    public String getDescription() {return description;}
    public List<Posts> getPosts() {return posts;}

    public void setId(String newId) {id=newId;}
    public void setTitle(String newTitle) {title=newTitle;}
    public void setAuthor(Users newAuthor) {author=newAuthor;}
    public void setCreateDate(Date newCreateDate) {createDate = newCreateDate;}
    public void setLastChange(Date newLastChange) {lastChange = newLastChange;}
    public void setDescription(String newDescription) {description = newDescription;}
    public void getPosts(List<Posts> newPosts) {posts=newPosts;}
}
