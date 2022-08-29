package com.jpmc.theater;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private String name;

    // create randomly generated unique user ID upon instantiation
    private String id = UUID.randomUUID().toString();

    /**
     * @param name customer name
     * @param id customer id
     */
    public Customer(String name) {
        this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getID() {
    	return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}