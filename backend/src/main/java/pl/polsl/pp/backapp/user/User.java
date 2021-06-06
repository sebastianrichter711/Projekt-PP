package pl.polsl.pp.backapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="users")
public class User {

    @Id
    private String id;
    private String email;
    private String login;
    @JsonIgnore
    private String password;
    private String role;
    private String status;
    private Integer postsNumber;
    private Date dateJoined;
    private Date lastLogin;
    @Nullable
    private java.sql.Date authoriseDate;
    @Nullable
    private java.sql.Date endAuthorise;

    public User(String email, String login, String password, String role, String status, Integer postsNumber, Date dateJoined,
                Date lastLogin) {
        this.email=email;
        this.login=login;
        this.password=password;
        this.role=role;
        this.status=status;
        this.postsNumber=postsNumber;
        this.dateJoined=dateJoined;
        this.lastLogin=lastLogin;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
    public Integer getPostsNumber() { return postsNumber; }
    public Date getDateJoined() { return dateJoined; }
    public Date getLastLogin() { return lastLogin; }
    public java.sql.Date getAuthoriseDate() { return authoriseDate; }
    public java.sql.Date getEndAuthorise() { return endAuthorise; }

    public void setId(String newId) { id=newId; }
    public void setEmail(String newEmail) { email=newEmail; }
    public void setLogin(String newLogin) {login=newLogin;}
    public void setPassword(String newPassword) { password=newPassword; }
    public void setRole(String newRole){ role=newRole; }
    public void setStatus(String newStatus) { status=newStatus; }
    public void setPostsNumber(Integer newPostsNumber){ postsNumber=newPostsNumber; }
    public void setDateJoined(Date newDateJoined){ dateJoined=newDateJoined; }
    public void setLastLogin(Date newLastLogin){ lastLogin=newLastLogin; }
    public void setAuthoriseDate(java.sql.Date newAuthoriseDate){ authoriseDate=newAuthoriseDate; }
    public void setEndAuthorise(java.sql.Date newEndAuthorise){ endAuthorise=newEndAuthorise; }

}
