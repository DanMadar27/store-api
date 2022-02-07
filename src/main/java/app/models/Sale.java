package app.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.sql.Timestamp;
import java.util.List;

@Document(collection = "sales")
public class Sale {
  
  @Id
  private String id;
  
  @DBRef
  private Customer customer;

  @DBRef
  private List<ProductSale> productSales;
  
  private double price;
  private String createdAt;
  private String updatedAt;

  public Sale() {}

  public Sale(Customer customer, List<ProductSale> productSales, double price) {
    this.customer = customer;
    this.productSales = productSales;
    this.price = price;
    this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    this.updatedAt = new Timestamp(System.currentTimeMillis()).toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<ProductSale> getProductSales() {
    return productSales;
  }

  public void setProductSales(List<ProductSale> productSales) {
    this.productSales = productSales;
  }

  public double getprice() {
    return price;
  }

  public void setprice(double price) {
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

  @Override
  public String toString() {
    return "id='" + id + '\'' +
      ", customer='" + customer.toString() + '\'' +
      ", productSales='" + productSales.toString() + '\'' +
      ", price='" + price + '\'' +
      ", createdAt='" + createdAt + '\'' +
      ", updatedAt='" + updatedAt + '\'';
  }
}