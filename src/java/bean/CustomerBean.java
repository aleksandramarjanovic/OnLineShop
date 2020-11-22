
package bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Baza;
import model.Customer;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name="customerBean", eager=true)
@ViewScoped
public class CustomerBean implements Serializable{
    
    private List<Customer> customers;
    private Customer selectedCustomer;
    private Customer newCustomer=new Customer();
    private boolean rowSelected = true;
  
   @PostConstruct
    public void init() {
        customers = Baza.getAllCustomers();
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Customer getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Customer newCustomer) {
        this.newCustomer = newCustomer;
   
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
    
    
    
    @Override
    public String toString() {
        return "CustomerBean{" + "customers=" + customers + ", selectedCustomer=" + selectedCustomer + '}';
    }
    
    public void onRowSelect(SelectEvent event) {
         this.rowSelected = false;
        FacesMessage msg = new FacesMessage("Customer Selected", ((Customer)event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
           this.rowSelected = true;
        FacesMessage msg = new FacesMessage("Customer Unselected", ((Customer)event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void deleteCustomer() {
        Baza.deleteCustomer(selectedCustomer.getId());
        selectedCustomer = null;
        customers = Baza.getAllCustomers();
    }
    
      public void addCustomer() {
        RequestContext.getCurrentInstance().execute("PF('customerDialog').hide()");
        Baza.addCustomer(newCustomer);
        customers = Baza.getAllCustomers();
        newCustomer = new Customer();
       
    }
      
      public void updateCustomer(){
          RequestContext.getCurrentInstance().execute("PF('updateCustomerDialog').hide()");
          Baza.updateCustomer(selectedCustomer);
          selectedCustomer= new Customer();
      
      }
    
}
