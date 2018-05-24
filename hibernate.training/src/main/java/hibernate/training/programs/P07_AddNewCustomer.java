package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.ContactInfo;
import hibernate.training.entity.Customer;
import hibernate.training.util.HibernateUtil;

public class P07_AddNewCustomer {
	
	public static void main(String[] args) {
		
		Customer c1 = new Customer("Shyam Sundar", "shyamsundarkc@gmail.com", "bigsecret");
		c1.setContactInfo(new ContactInfo("RBI lyt", "Bangalore", "Karnataka", "India", "9731424888"));
		
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		session.persist(c1);
		session.getTransaction().commit();
		
		System.out.println("Customer data saved!");
		
		session.close();
		factory.close();
		
	}

}
