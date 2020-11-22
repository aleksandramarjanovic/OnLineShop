package bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Baza;
import model.Customer;
import model.Product;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ViewScoped
public class ShopBean {
    private boolean rowSelected = true;
    private List<Product> shopProducts;
    private Customer currentCustemer = new Customer();
    private Product shopSelectedProduct;
    private int quantity;
    
    @PostConstruct
    public void init() {
        shopProducts = Baza.getAllProducts();
    }   

    public Customer getCurrentCustemer() {
        return currentCustemer;
    }

    public void setCurrentCustemer(Customer currentCustemer) {
        this.currentCustemer = currentCustemer;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

    public List<Product> getShopProducts() {
        return shopProducts;
    }

    public void setShopProducts(List<Product> shopProducts) {
        this.shopProducts = shopProducts;
    }

    public Product getShopSelectedProduct() {
        return shopSelectedProduct;
    }

    public void setShopSelectedProduct(Product shopSelectedProduct) {
        this.shopSelectedProduct = shopSelectedProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

    public String validateEmailPassword() {
        currentCustemer = Baza.findEmail(currentCustemer.getEmail(), currentCustemer.getPassword());
        if (currentCustemer == null) {
            currentCustemer = new Customer();
            //prikazi gresku
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "shop";
        } else {
            return "curentShop";
        }

    }
    
      public void onRowSelect(SelectEvent event) {
        this.rowSelected = false;
        FacesMessage msg = new FacesMessage("Product selected", ((Product)event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        this.rowSelected = true;
        FacesMessage msg = new FacesMessage("Product Unselected", ((Product)event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void buyProduct(){
        shopSelectedProduct = Baza.getProductById(shopSelectedProduct.getId());
        int oldQuantity = shopSelectedProduct.getQuantity();
        if ( oldQuantity < this.quantity){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "There aren't that many products in stock", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            int newQuantity = oldQuantity - this.quantity;
            if(newQuantity==0){
              Baza.delete(shopSelectedProduct.getId());
              rowSelected = true;
            }
            shopSelectedProduct.setQuantity(newQuantity);
            Baza.updateProduct(shopSelectedProduct);
            shopProducts = Baza.getAllProducts();
            RequestContext.getCurrentInstance().execute("PF('BuyDialog').hide()");
            this.quantity = 0;
        }
        
    }

    @Override
    public String toString() {
        return "ShopBean{" + "rowSelected=" + rowSelected + ", shopProducts=" + shopProducts + ", currentCustemer=" + currentCustemer + ", shopSelectedProduct=" + shopSelectedProduct + ", quantaty=" + quantity + '}';
    }
    
}


