package hibernate.training.programs;

import org.hibernate.Session;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P04_AddProductsToNewBrand {

	public static void main(String[] args) {
		
		// a new brand
		Brand b = new Brand("Dell");
		
		// new product in the brand
		Product p1 = new Product();
		p1.setName("Optical Mouse");
		p1.setUnitPrice(299.9);
		b.addProduct(p1);
		
		// another new product in the brand
		p1 = new Product();
		p1.setName("LCD Monitor");
		p1.setUnitPrice(4499.9);
		b.addProduct(p1);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(b);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Done!");
	}
}
