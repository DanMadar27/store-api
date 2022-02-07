package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Document(collection = "people")
public class Person {
  
  @Id
  private String id;
  
  private String firstName;
  private String lastName;
  private String birth;
  private String country;
  private String city;
  private String address;
  private String zip;
  private String phone;
  private String email;
  private String createdAt;
  private String updatedAt;

  public Person() {}

  public Person(String firstName, String lastName, String birth,
                String country, String city, String address,
                String zip, String phone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birth = birth;
    this.country = country;
    this.city = city;
    this.address = address;
    this.zip = zip;
    this.phone = phone;
    this.email = email;
    this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    this.updatedAt = new Timestamp(System.currentTimeMillis()).toString();
  }

  public Person(Person person) {
    this.id = person.id;
    this.firstName = person.firstName;
    this.lastName = person.lastName;
    this.birth = person.birth;
    this.country = person.country;
    this.city = person.city;
    this.address = person.address;
    this.zip = person.zip;
    this.phone = person.phone;
    this.email = person.email;
    this.createdAt = person.createdAt;
    this.updatedAt = person.updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    return "id='" + id + '\'' +
      ", name='" + firstName + lastName + '\'' +
      ", birth='" + birth + '\'' +
      ", country='" + country + '\'' +
      ", city='" + city + '\'' +
      ", address='" + address + '\'' +
      ", zip='" + zip + '\'' +
      ", phone='" + phone + '\'' +
      ", email='" + email + '\'' +
      ", createdAt='" + createdAt + '\'' +
      ", updatedAt='" + updatedAt + '\'';
  }
}