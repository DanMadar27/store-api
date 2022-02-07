package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "customers")
public class Customer {
  
  @Id
  private String id;

  @DBRef
  private Person person;

  @DBRef
  private User user;

  private String credit;

  public Customer() {}

  public Customer(Person person) {
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

  public String getCredit() {
    return credit;
  }

  public void setCredit(String credit) {
    this.credit = credit;
  }

  @Override
  public String toString() {
    return "id='" + id + "\' " +
      person.toString() + " " +
      user.toString() +
      " credit='" + credit + "\'";
  }
}