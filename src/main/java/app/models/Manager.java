package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "managers")
public class Manager {
  
  @Id
  private String id;

  @DBRef
  private Person person;

  @DBRef
  private User user;

  public Manager() {}

  public Manager(Person person) {
    this.person = new Person(person);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "id='" + id + "\' " +
      person.toString() + " " +
      user.toString();
  }
}