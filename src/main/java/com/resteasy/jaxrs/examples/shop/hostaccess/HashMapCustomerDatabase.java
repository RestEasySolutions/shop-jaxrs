package com.resteasy.jaxrs.examples.shop.hostaccess;

import com.resteasy.jaxrs.examples.shop.domain.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ant Brown on 12/05/2016.
 */
public class HashMapCustomerDatabase implements CustomerDatabase {

	private static CustomerDatabase instance;

	private static Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();

	public static CustomerDatabase getInstance() {
		if (instance == null) {
			// Call a synchronized instantiation method to make this thread safe
			// with no impact on performance
			instantiate();
		}
		return instance;
	}

	/*
	 * Synchonized instantiate (this should only get called once) so the synchronized shouldn't affect performance
	 */
	private synchronized static void instantiate() {
		if (instance == null) {
			instance = new HashMapCustomerDatabase();
		}
	}

	@Override
	public Customer get(int id) {
		System.out.println("Get from HashMapCustomerDatabase");
		return customerDB.get(id);
	}

	@Override
	public void put(int id, Customer customer) {
		System.out.println("Put to HashMapCustomerDatabase");
		customerDB.put(id, customer);
	}

}
