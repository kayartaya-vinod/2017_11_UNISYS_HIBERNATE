package hibernate.training.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.training.entity.Brand;
import hibernate.training.util.HibernateUtil;

public class P02_AddNewBrand {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Brand b = new Brand("Adidasssss");
		session.beginTransaction();

		try {
			session.save(b);
			session.getTransaction().commit();
			System.out.println("New brand saved!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Transaction failed.");
		}

		session.close();
		factory.close();
	}

}
