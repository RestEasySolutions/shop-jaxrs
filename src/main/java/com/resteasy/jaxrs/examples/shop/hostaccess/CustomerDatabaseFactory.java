package com.resteasy.jaxrs.examples.shop.hostaccess;

/**
 * Created by Ant Brown on 12/05/2016.
 */
public class CustomerDatabaseFactory {

	public CustomerDatabase getCustomerDatabase(String databaseMode) {
		System.out.println("In Factory");
		if (databaseMode.equals("HashMap")) {
			CustomerDatabase db = HashMapCustomerDatabase.getInstance();
			System.out.println("instance" + db);
			return db;
		} else {
			System.out.println("null");
			return null;
		}
	}
}
