package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Employee;
import hibernate.training.entity.Laptop;
import hibernate.training.util.HibernateUtil;

public class P06_AddEmployeesLaptops {

	public static void main(String[] args) {
		// saved along with l2
		Employee e1 = new Employee("John", "Admin");
		
		// need to explicitly save; not associated with any laptop
		Employee e2 = new Employee("Jane", "Accounting");
		
		// save these two:
		Laptop l1 = new Laptop("ASD123", "Dell", "Inspiron");
		Laptop l2 = new Laptop("XYZ222", "Apple", "Macbook Pro");
		
		l2.setOwnedBy(e1); // does a bi-directional association
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		// l1, l2, e1, e2 --> transient here
		
		session.persist(e2);
		session.persist(l1);
		session.persist(l2);
		
		// l1, l2, e1, e2 --> persistent here
		session.getTransaction().commit();
		session.close();
		factory.close();
		
		// l1, l2, e1, e2 --> detached here
		System.out.println("Done!");
	}
}
