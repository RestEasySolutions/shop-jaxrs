package com.resteasy.jaxrs.examples.shop.domain;

/**
 * Domain class to hold the customer details
 * Created by Ant Brown on 02/02/2016.
 */
public class CustomerResponse {

	private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
