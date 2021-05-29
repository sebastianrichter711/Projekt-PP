package pl.polsl.pp.backapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="posts")
public class Posts {

    @Id
    private String id;
    private Users author;
    private Date createDate;
    private Date lastChange;
    private String text;

    public Posts(Users author, Date createDate, Date lastChange, String text){
        this.author = author;
        this.createDate = createDate;
        this.lastChange = lastChange;
        this.text=text;
    }

    public String getId() {return id;}
    public Users getAuthor() {return author;}
    public Date getCreateDate() {return createDate;}
    public Date getLastChange() {return lastChange;}
    public String getText() {return text;}

    public void setId(String newId) {id=newId;}
    public void setAuthor(Users newAuthor) {author=newAuthor;}
    public void setCreateDate(Date newCreateDate) {createDate = newCreateDate;}
    public void setLastChange(Date newLastChange) {lastChange = newLastChange;}
    public void setText(String newText) {text = newText;}

}
