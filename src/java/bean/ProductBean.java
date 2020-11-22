
package bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Baza;
import model.Product;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean
@ViewScoped
public class ProductBean {
       
    private List<Product>products;
    private Product selectedProduct;
    private Product newProduct = new Product();
    private boolean rowSelected = true;
    
    @PostConstruct
    public void init() {
        products = Baza.getAllProducts();
    }

   
    public ProductBean() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    @Override
    public String toString() {
        return "ProductBean{" + "products=" + products + ", selectedProduct=" + selectedProduct + '}';
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
    
    public void deleteProduct() {       
        Baza.delete(selectedProduct.getId());
        selectedProduct = null;
        products = Baza.getAllProducts();
         
    }
    
    public  void addProduct() {
      
        RequestContext.getCurrentInstance().execute("PF('productDialog').hide()");
        Baza.addProduct(newProduct);   
        products = Baza.getAllProducts();
        newProduct = new Product();
        
    }
    
    public void updateProduct(){
        RequestContext.getCurrentInstance().execute("PF('updateDialog').hide()");
        Baza.updateProduct(selectedProduct);
        selectedProduct = new Product();
    
    }

    
    
}
