package app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "products")
public class Product {
  
  @Id
  private String id;
  
  // @DBRef
  // private Supplier supplier;

  private String name;
  private String category;
  private String type;
  private double price;
  private String createdAt;
  private String updatedAt;
  private String deletedAt;

  public Product() {}

  public Product(String name, String category, String type, double price) {
    this.name = name;
    this.category = category;
    this.type = type;
    this.price = price;
    this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    this.updatedAt = new Timestamp(System.currentTimeMillis()).toString();
    this.deletedAt = null;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
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

  public String getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public String toString() {
    return "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", category='" + category + '\'' +
      ", type='" + type + '\'' +
      ", price='" + price + '\'' +
      ", createdAt='" + createdAt + '\'' +
      ", updatedAt='" + updatedAt + '\'' +
      ", deletedAt='" + deletedAt + '\'';
  }
}