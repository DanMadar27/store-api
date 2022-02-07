package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Document(collection = "suppliers")
public class Supplier {
  
  @Id
  private String id;
  
  private String name;
  private String country;
  private String city;
  private String address;
  private String zip;
  private String phone;
  private String email;
  private String createdAt;
  private String updatedAt;

  public Supplier() {}

  public Supplier(String name, String country, String city, String address,
                  String zip, String phone, String email) {
    this.name = name;
    this.country = country;
    this.city = city;
    this.address = address;
    this.zip = zip;
    this.phone = phone;
    this.email = email;
    this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    this.updatedAt = new Timestamp(System.currentTimeMillis()).toString();
  }

  public Supplier(Supplier supplier) {
    this.name = supplier.name;
    this.country = supplier.country;
    this.city = supplier.city;
    this.address = supplier.address;
    this.zip = supplier.zip;
    this.phone = supplier.phone;
    this.email = supplier.email;
    this.createdAt = supplier.createdAt;
    this.updatedAt = supplier.updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
      ", name='" + name + '\'' +
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