package hibernate.training.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	private Integer id;
	private Customer customer;
	private Date orderDate = new Date();
	private String status = "PENDING";
	
	
	// one or more line items
	private Set<LineItem> lineItems = new HashSet<LineItem>();

	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// readable property called "lineItems"
	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	// writable property called "lineItems"
	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	// helper method for bi-directional association
	public void addLineItem(LineItem item){
		this.lineItems.add(item);
		item.setOrder(this);
	}

}







