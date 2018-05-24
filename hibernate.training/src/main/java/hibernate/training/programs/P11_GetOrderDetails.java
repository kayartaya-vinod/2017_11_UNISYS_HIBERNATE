package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Customer;
import hibernate.training.entity.LineItem;
import hibernate.training.entity.Order;
import hibernate.training.util.HibernateUtil;

public class P11_GetOrderDetails {
	
	public static void main(String[] args) {
		
		Integer orderId = 2;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		Order ord1 = session.get(Order.class, orderId);
		System.out.println("Order details for id : " + ord1.getId());
		System.out.println("Order date: " + ord1.getOrderDate());
		System.out.println("Status: " + ord1.getStatus());
		
		// customer details;
		Customer c1 = ord1.getCustomer();
		System.out.println("Customer Name: " + c1.getName());
		System.out.println("From: " + c1.getContactInfo().getCity());
		System.out.println("Email: " + c1.getEmail());
		
		System.out.println("Prducts in this order...:");
		System.out.printf("%-20s %10s %10s %10s\n",
				"Name", "Price", "Qty", "Amount");
		System.out.println("------------------------------------");
		for(LineItem li: ord1.getLineItems()){
			System.out.printf("%-20s %10.2f %10d %10.2f\n",
					li.getProduct().getName(),
					li.getUnitPrice(),
					li.getQuantity(),
					li.getUnitPrice()*li.getQuantity()
					);
		}
		session.close();
		factory.close();
	}
}
