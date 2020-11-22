
package model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="customer")
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer id;
      
    @Column (name="name")
    private String name;
      
    @Column (name="surname")
    private String surname;
       
    @Column (name="password")
    private String password;
        
    @Column (name="email")
    private String email;
    
    @Column (name="phone_number")
    private String phone_number;
          
    @Column (name="address")
    private String address;
    

    public Customer() {
    }

    public Customer(String name, String surname, String password, String email, String phone_number, String address) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", password=" + password + ", email=" + email + ", phone_number=" + phone_number + ", address=" + address + '}';
    }
        
}
