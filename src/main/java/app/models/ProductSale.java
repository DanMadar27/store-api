package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.sql.Timestamp;

@Document(collection = "productSales")
public class ProductSale {
  
  @Id
  private String id;
  
  @DBRef
  private Product product;

  private boolean isSold;
  private String createdAt;
  private String updatedAt;
  private String deletedAt;

  public ProductSale() {}

  public ProductSale(Product product) {
    this.product = product;
    this.isSold = false;
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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public boolean getIsSold() {
    return isSold;
  }

  public void setIsSold(boolean isSold) {
    this.isSold = isSold;
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
    return "id='" + id + "\' " +
      product.toString() + 
      ", isSold='" + isSold + '\'' +
      ", createdAt='" + createdAt + '\'' +
      ", updatedAt='" + updatedAt + '\'' +
      ", deletedAt='" + deletedAt + '\'';
  }
}