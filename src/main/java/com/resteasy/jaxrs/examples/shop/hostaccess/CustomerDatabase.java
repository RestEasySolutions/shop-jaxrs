package com.resteasy.jaxrs.examples.shop.hostaccess;

import com.resteasy.jaxrs.examples.shop.domain.Customer;

/**
 * Created by Ant Brown on 12/05/2016.
 */
public interface CustomerDatabase {
	Customer get(int id);

	void put(int id, Customer customer);
}
