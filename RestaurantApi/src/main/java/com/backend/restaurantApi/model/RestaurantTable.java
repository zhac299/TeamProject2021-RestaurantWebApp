package com.backend.restaurantApi.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Creates an SQL table that will handle the tables information.
 */
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
    
    /**
     * The primary key of the table.
     * It represents the table number.
     */
    @Id
    @Column(name = "tableNumber", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tableNumber;

    /**
     * A column of the table that tells if a table needs help or not.
     */
    @Column(name = "needsHelp", nullable = false)
    private boolean needsHelp;

    /**
     * A column of the table that tells if a table is occupied or not.
     */
    @Column(name = "isOccupied", nullable = false)
    private boolean isOccupied;

    /**
     * A column that stores all the customers seated a table.
     */
    @JsonManagedReference(value = "restaurant_table")
    @Column(name = "customer", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy="table")
    private List<Customer> customer;

    /**
     * A getter for the private class field customer.
     * 
     * @return a list of customers seated at a table
     */
    public List<Customer> getCustomer() {
        return this.customer;
    }
   
    /**
     * Sets the customer list to a new one.
     * 
     * @param newCustomer the new customer list
     */
    public void setCustomer(List<Customer> newCustomer) {
        this.customer = newCustomer;
    }

    /**
     * A getter for the private class field tableNumber.
     * 
     * @return the table number
     */
    public long getTableNumber() {
        return this.tableNumber;
    }

    /**
     * A getter for the private clas field needsHelp.
     * 
     * @return the table needsHelp field
     */
    public boolean getNeedsHelp() {
        return this.needsHelp;
    }

    /**
     * A getter for the private class field isOccupied.
     * 
     * @return the table isOccupied field
     */
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    /**
     * Setter that updates the tableNumber with a new one.
     * 
     * @param newTableNumber the new table number
     */
    public void setTableNumber(long newTableNumber) {
        this.tableNumber = newTableNumber;
    }

    /**
     * Setter that updates the needsHelp field.
     * 
     * @param needsHelp the new needsHelp field
     */
    public void setNeedsHelp(boolean needsHelp) {
        this.needsHelp = needsHelp;
    }

    /**
     * Setter that updates the isOccupied field.
     * 
     * @param isOccupied the new isOccupied field
     */
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Returns A Jason object containing all the tables details.
     */
    @Override
    public String toString() {
        return "RestaurantTables{" +
            "tableNumber='" + this.tableNumber +
            ", needsHelp='" + this.needsHelp + '\'' +
            ", IsOccupied='" + this.isOccupied + '\'' +
            '}';
    }

    public boolean isNeedsHelp() {
        return needsHelp;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}