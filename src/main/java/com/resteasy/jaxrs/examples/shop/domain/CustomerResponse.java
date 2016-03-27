package com.resteasy.jaxrs.examples.shop.domain;

/**
 * Created by Ant Brown on 02/02/2016.
 */
public class CustomerResponse {

    public Customer getCustomer() {
        return customer;
    }

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
