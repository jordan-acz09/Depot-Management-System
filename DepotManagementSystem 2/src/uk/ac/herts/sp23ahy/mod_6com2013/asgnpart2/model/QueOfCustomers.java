package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import java.util.ArrayList;
import java.util.Collections;

public class QueOfCustomers {
    private ArrayList <Customer> queOfCustomers;

    // Constructor to initialize the queue
    public QueOfCustomers() {
        queOfCustomers = new ArrayList<Customer>(); 
    }

    public boolean isEmpty() {
        return queOfCustomers.isEmpty();
    }

    public int getSize() {
        return queOfCustomers.size();
    }

    public Customer findById(String id) {
        for (Customer c : queOfCustomers) {
            if(c.getParcel().equals(id))
            {
                return c;    
            }
        }
        return null;
    }
    
        public void addCustomer(Customer customer) {
        queOfCustomers.add(customer);
    }
    
    public String listDetails()
    {
        StringBuffer allEntries = new StringBuffer();
        for (Customer details : queOfCustomers){
             allEntries.append(details);
             allEntries.append('\n');
        }
        return allEntries.toString();
    }
    
    public String listByID() {
        Collections.sort(queOfCustomers, (c1, c2) -> c1.getParcel().getParcelID().compareTo(c2.getParcel().getParcelID()));
        return listDetails();
    }

    public String listByName() {
        Collections.sort(queOfCustomers, (c1, c2) -> c1.getLastName().compareTo(c2.getLastName()));
        return listDetails();
    }
}
