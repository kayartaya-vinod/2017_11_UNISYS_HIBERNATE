package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.training.entity.Brand;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P05_UpdateExistingBrandProducts {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		try {
			
			Brand b = session.get(Brand.class, 18);
			// "b" is a persistent object
			// changes to the mapped scalar properties will trigger
			// an SQL update execution
			b.setName("Dell Computers");
			
			Product p = new Product();
			p.setName("Keyboard");
			p.setUnitPrice(455.0);
			b.addProduct(p); // "changes" the mapped "products" property
			// @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
			// need explicit calls to persit() or merge() methods
			session.merge(b);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		
		
		session.close();
		factory.close();
		
		
	}
}
