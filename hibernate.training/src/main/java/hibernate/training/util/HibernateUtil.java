package hibernate.training.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Category;
import hibernate.training.entity.Customer;
import hibernate.training.entity.Employee;
import hibernate.training.entity.Laptop;
import hibernate.training.entity.LineItem;
import hibernate.training.entity.Product;

public class HibernateUtil {
	static StandardServiceRegistry serviceRegistry;

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		Properties props = configuration.getProperties();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(props).build();

		// Add your annotated class here:

		configuration.addAnnotatedClass(Brand.class);
		configuration.addAnnotatedClass(Category.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Laptop.class);
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Customer.class);
		// configuration.addAnnotatedClass(LineItem.class);
		
		// configuration.addFile("src/main/java/hibernate/training/entity/customer.hbm.xml");
		configuration.addFile("src/main/java/hibernate/training/entity/order.hbm.xml");
		configuration.addFile("src/main/java/hibernate/training/entity/lineitem.hbm.xml");

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void closeRegistry() {
		if (serviceRegistry != null) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}
	}
}
