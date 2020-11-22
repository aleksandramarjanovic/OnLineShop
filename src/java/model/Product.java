
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "product")
public class Product implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id")
    private int id;
       
    @Column (name="name")
    private String name;
       
    @Column (name="price")
    private double price;
       
    @Column (name="description")
    private String description;
       
    @Column (name="quantity")
    private int quantity;

    public Product(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
        
    
     
    public Product() {
         
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantinity) {
        this.quantity = quantinity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", quantity=" + quantity + '}';
    }
     
}
