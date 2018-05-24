package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Customer;
import hibernate.training.entity.LineItem;
import hibernate.training.entity.Order;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P09_AddOrderWithLineItems {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		try {
			
			// get the customer with id 1
			Customer c1 = session.get(Customer.class, 1);
			
			// create a new Order object 
			Order o1 = new Order();
			o1.setCustomer(c1);
			
			// create few line items
			// in practical scenario, this will be in HTTP session of the user
			// as a list of LineItem objects without any Order object
			
			LineItem li1 = new LineItem();
			Product p = session.get(Product.class, 22);
			li1.setProduct(p);
			li1.setUnitPrice(p.getUnitPrice());
			li1.setQuantity(3);
			o1.addLineItem(li1); // does bi-directional association --> li1.setOrder(o1)
			
			li1 = new LineItem();
			p = session.get(Product.class, 12);
			li1.setProduct(p);
			li1.setUnitPrice(p.getUnitPrice());
			li1.setQuantity(2);
			o1.addLineItem(li1); // does bi-directional association --> li1.setOrder(o1)

			li1 = new LineItem();
			p = session.get(Product.class, 76);
			li1.setProduct(p);
			li1.setUnitPrice(p.getUnitPrice());
			li1.setQuantity(5);
			o1.addLineItem(li1); // does bi-directional association --> li1.setOrder(o1)
			
			session.save(o1);
			
			session.getTransaction().commit();
			System.out.println("Order saved!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Order not saved!!!");
		}
		session.close();
		factory.close();
		
	}
}
