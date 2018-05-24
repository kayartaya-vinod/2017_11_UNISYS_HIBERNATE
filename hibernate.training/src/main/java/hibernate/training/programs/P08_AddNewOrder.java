package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Customer;
import hibernate.training.entity.Order;
import hibernate.training.util.HibernateUtil;

public class P08_AddNewOrder {
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		// persitent object
		Customer c1 = session.get(Customer.class, 1);
		
		// transient object
		Order o1 = new Order();
		o1.setCustomer(c1);
		
		session.save(o1);
		
		session.getTransaction().commit();
		System.out.println("Order saved!");
		session.close();
		factory.close();
	}

}
