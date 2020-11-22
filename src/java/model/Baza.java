
package model;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Baza {

    public static Session session;
    public static Transaction tx;
    public static Connection con;
      public static  Statement statement;

    public static void createConnection() {
        
        if(session == null){
            session = HibernateUtil.createSessionFactory().openSession();
            tx = null;
            try {
                tx = session.beginTransaction();
                Customer customer1 = new Customer("Aleksandra", "Arsic", "1234kj", "aleks@gmail.com", "+381 64123456", "Blok V Koprivice 16");
                Customer customer2 = new Customer("Zivko", "Zivkovic", "av1256", "zika@gmail.com","+381 63456123", "Karadjordjeva 14");
                Product product1 = new Product ("dress", 120.00,"Size s",4);
                Product product2 = new Product ("t-shirt", 20.00," Size m, new colection",3);
                session.save(customer1);
                session.save(customer2);
                session.save(product1);
                session.save(product2);
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println(e);
            } finally {
                HibernateUtil.close();
            }
        }
    }

    public static List<Customer> getAllCustomers() {
        Baza.createConnection();
        tx = null;
        List<Customer> osobe = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            String hql = "from Customer";
            Query query = session.createQuery(hql);
            osobe = query.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            HibernateUtil.close();
        }
        return osobe;

    }
    
     public static void addCustomer(Customer newCustomer) {
                tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newCustomer);
            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }

    public static void deleteCustomer(int id) {
        tx = null;
        try {
            tx = session.beginTransaction();
            
            String hql = "delete Customer as c where c.id = :id";
            session.createQuery(hql)
                    .setInteger("id", id)
                    .executeUpdate();

            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }
    
    public static void updateCustomer(Customer selectedCustomer) {
        
          tx = null;
        try {
            tx = session.beginTransaction();
            
            session.evict(selectedCustomer);

            String hql = "update Customer as c set c.name= :name, c.surname= :surname, c.password= :password, c.email = :email, c.phone_number = :phone_number, c.address= :address where c.id= :id";
            int brojUpdatovanih = session.createQuery(hql)
                .setString("name",selectedCustomer.getName())
                .setString("surname", selectedCustomer.getSurname())
                .setString("password", selectedCustomer.getPassword())
                .setString("email", selectedCustomer.getEmail())
                .setString("phone_number", selectedCustomer.getPhone_number())
                .setString("address", selectedCustomer.getAddress())
                .setInteger("id", selectedCustomer.getId())
                .executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
         HibernateUtil.close();
        }
    }

    public static List<Product> getAllProducts() {
        Baza.createConnection();
        tx = null;
        List<Product>products= new ArrayList<>();
        try {
            tx = session.beginTransaction();
            String hql = "from Product";
            Query query = session.createQuery(hql);
            products = query.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            HibernateUtil.close();
        }
        return products;

    }

    public static void delete(int id) {
        
                tx = null;
        try {
            tx = session.beginTransaction();
            
            String hql = "delete Product as p where p.id = :id";
            session.createQuery(hql)
                    .setInteger("id", id)
                    .executeUpdate();

            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
        
    }

    public static void addProduct(Product added) {
        tx = null;
        try {
            tx = session.beginTransaction();
            session.save(added);
            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close();
        }
    }

    public static void updateProduct(Product selectedProduct) {
        tx = null;
        try {
            tx = session.beginTransaction();
            
            session.evict(selectedProduct);

            String hql = "update Product as p set p.name= :name, p.price= :price, p.description= :description, p.quantity = :quantity where p.id= :id";
            int brojUpdatovanih = session.createQuery(hql)
                .setString("name",selectedProduct.getName())
                .setString("description", selectedProduct.getDescription())
                .setDouble("price", selectedProduct.getPrice())
                .setInteger("quantity", selectedProduct.getQuantity())
                .setInteger("id", selectedProduct.getId())
                .executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
         HibernateUtil.close();
        }
    }

    public static Customer findEmail(String email, String password) {
        Baza.createConnection();
        tx=null;
        try {
            tx = session.beginTransaction();
            Customer customer;
            String hql = "from Customer as c where c.email=:email AND c.password=:password";
            Object object = session.createQuery(hql)
                .setString("email", email)
                .setString("password", password)
                .uniqueResult();
            customer = (Customer) object;
            tx.commit();
            return  customer;
        } catch (HibernateException e) {
             System.out.println(e);
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            HibernateUtil.close();
        
        }
        
        return null;
    }

    public static Product getProductById(int id) {
           tx = null;
        Product selection = new Product();
        try {
            tx = session.beginTransaction();
            selection = (Product) session.get(Product.class, id);
            if(selection!=null){
            session.evict(selection);
            }
            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

        } finally {

           HibernateUtil.close();

        }
        return selection;
     
    }


}