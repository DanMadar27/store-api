package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Document(collection = "users")
public class User {
  
  @Id
  private String id;
  
  private String username;
  private String password;
  private String createdAt;
  private String updatedAt;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    this.updatedAt = new Timestamp(System.currentTimeMillis()).toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
   
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "id='" + id + "\' " +
      ", username='" + username + "\' " +
      ", password='" + password + "\'" +
      ", createdAt='" + createdAt + '\'' +
      ", updatedAt='" + updatedAt + '\'';
 
  }
}