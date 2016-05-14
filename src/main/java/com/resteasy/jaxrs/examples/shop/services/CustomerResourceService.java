package com.resteasy.jaxrs.examples.shop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resteasy.jaxrs.examples.shop.domain.Customer;
import com.resteasy.jaxrs.examples.shop.domain.CustomerResponse;
import com.resteasy.jaxrs.examples.shop.hostaccess.CustomerDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ant Brown on 21/01/2016.
 */
@Controller
@Component
public class CustomerResourceService implements CustomerResource {

	private static AtomicInteger idCounter = new AtomicInteger();
	@Autowired
	private CustomerDatabase customerDatabase;

	@Override
	public Response createCustomer(InputStream is) {
		System.out.println("=================================== IN CREATE CUSTOMER");

		if (customerDatabase == null) {
			System.out.println("CustomerDatabase is null");

		}
		Customer customer = readCustomerFromJson(is);
		customer.setId(idCounter.incrementAndGet());
		customerDatabase.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}

	@Override
	public StreamingOutput getCustomer(@PathParam("id") int id) {
		System.out.println("=================================== IN GET CUSTOMER");
		final Customer customer = customerDatabase.get(id);
		CustomerResponse response = new CustomerResponse();
		response.setCustomer(customer);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomerAsJson(outputStream, response);
			}
		};
	}

	@Override
	public void updateCustomer(@PathParam("id") int id, InputStream is) {
		Customer update = readCustomerFromXml(is);
		Customer current = customerDatabase.get(id);
		if (current == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}

	protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<customer id=\"" + cust.getId() + "\">");
		writer.println("   <first-name>" + cust.getFirstName() + "</first-name>");
		writer.println("   <last-name>" + cust.getLastName() + "</last-name>");
		writer.println("   <street>" + cust.getStreet() + "</street>");
		writer.println("   <city>" + cust.getCity() + "</city>");
		writer.println("   <state>" + cust.getState() + "</state>");
		writer.println("   <zip>" + cust.getZip() + "</zip>");
		writer.println("   <country>" + cust.getCountry() + "</country>");
		writer.println("</customer>");
	}

	protected void outputCustomerAsJson(OutputStream outputStream, CustomerResponse customer) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, customer);
	}

	protected Customer readCustomerFromXml(InputStream is) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			Customer cust = new Customer();
			if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals("")) {
				cust.setId(Integer.valueOf(root.getAttribute("id")));
			}
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("first-name")) {
					cust.setFirstName(element.getTextContent());
				} else if (element.getTagName().equals("last-name")) {
					cust.setLastName(element.getTextContent());
				} else if (element.getTagName().equals("street")) {
					cust.setStreet(element.getTextContent());
				} else if (element.getTagName().equals("city")) {
					cust.setCity(element.getTextContent());
				} else if (element.getTagName().equals("state")) {
					cust.setState(element.getTextContent());
				} else if (element.getTagName().equals("zip")) {
					cust.setZip(element.getTextContent());
				} else if (element.getTagName().equals("country")) {
					cust.setCountry(element.getTextContent());
				}
			}
			return cust;
		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
	}

	protected Customer readCustomerFromJson(InputStream inputStream) {
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = null;
		try {
			customer = mapper.readValue(inputStream, Customer.class);
		} catch (IOException ioe) {
			throw new WebApplicationException(ioe, Response.Status.BAD_REQUEST);
		}
		return customer;
	}

}
