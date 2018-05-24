package hibernate.training.programs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import hibernate.training.entity.Category;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P13_TestingCriteriaAPI {

	static Session session = null;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();

		// getAllCategories();
		getProductsByPriceRange(5.0, 10.0); // min->Rs.5, max->Rs.10
		// getProductsByPage(5); // 5 --> pageNum, pageSize is 10
		// getProductInfo(); // name, price, brand
		// getBrandwiseProductCount();

		session.close();
		factory.close();
	}

	static void getProductsByPriceRange(double min, double max) {
		Criteria crit = session.createCriteria(Product.class);
		
		crit.add(Restrictions.ge("unitPrice", min));
		crit.add(Restrictions.le("unitPrice", max));
		
		List<Product> list = crit.list();
		
	}

	static void getAllCategories() {
		Criteria crit = session.createCriteria(Category.class);
		List<Category> list = crit.list();
		for(Category c: list){
			System.out.println(c.getId() + " --> " + c.getName());
		}
	}
}










