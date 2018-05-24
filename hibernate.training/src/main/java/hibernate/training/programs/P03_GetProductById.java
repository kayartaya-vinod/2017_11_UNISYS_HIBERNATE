package hibernate.training.programs;

import org.hibernate.Session;

import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P03_GetProductById {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Product p1 = session.get(Product.class, 22);
		session.close();
		
		System.out.println("Product name = " + p1.getName());
		System.out.println("Brand = " + p1.getBrand().getName());
		System.out.println("Category = " + p1.getCategory().getName());
	}
}
