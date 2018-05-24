package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P01_GetBrandById {
	
	public static void main(String[] args) {
		
		// represents a DB
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		// represents a DB connection (close ASAP)
		Session session = factory.openSession();
		
		// use the session
		Brand b1 = (Brand) session.get(Brand.class, 3);
		// hibernate creates a new Brand object, and names it
		// as "persistent" object
		System.out.println("Name of the brand = " + b1.getName());
		System.out.printf("Total %d products\n", b1.getProducts().size());
		for(Product p: b1.getProducts()){
			System.out.println("\t" + p.getName());
		}
		// close all open resources
		session.close();
		factory.close();
		
	}

}
