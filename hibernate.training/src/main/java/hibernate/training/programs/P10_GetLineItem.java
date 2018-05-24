package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.LineItem;
import hibernate.training.util.HibernateUtil;

public class P10_GetLineItem {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		LineItem key = new LineItem(3, 76);
		LineItem li = session.get(LineItem.class, key);
		
		System.out.println("Order id : " + li.getOrder().getId());
		System.out.println("Order date: " + li.getOrder().getOrderDate());
		
		System.out.println("Product name: " + li.getProduct().getName());
		System.out.println("Product desc: " + li.getProduct().getDescription());
		
		System.out.println("Quantity: " + li.getQuantity());

		
		session.close();
		factory.close();
	}
}
