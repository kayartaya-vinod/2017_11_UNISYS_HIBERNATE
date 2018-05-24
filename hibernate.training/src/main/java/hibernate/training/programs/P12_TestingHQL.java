package hibernate.training.programs;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hibernate.training.entity.Category;
import hibernate.training.entity.Product;
import hibernate.training.util.HibernateUtil;

public class P12_TestingHQL {
	
	static Session session = null;
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();
		
		// getAllCategories();
		// getProductsByPriceRange(5.0, 10.0); // min->Rs.5, max->Rs.10
		// getProductsByPage(5); // 5 --> pageNum, pageSize is 10
		// getProductInfo(); // name, price, brand
		// getBrandwiseProductCount();
		
		getOrderTotal(2); // get the total order amount for order id 2
		
		session.close();
		factory.close();
	}

	@SuppressWarnings("unused")
	static void getOrderTotal(int orderId) {
		String sql = "select sum(quantity*unit_price) as total "
				+ "from line_items where order_id = :ORDER_ID";
		
		SQLQuery qry = session.createSQLQuery(sql);
		qry.setParameter("ORDER_ID", orderId);
		
		Double total = (Double) qry.uniqueResult();
		System.out.println("Total order amount is " + total);
		
		
	}

	static void getBrandwiseProductCount() {
		String hql = "select p.brand.name, count(p) "
				+ "from Product p group by p.brand.name";
		Query<Object[]> qry = session.createQuery(hql, Object[].class);
		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.printf("%s --> %d\n", 
					data[0], data[1]);
		}
	}

	static void getProductInfo() {
		String hql = "select name, unitPrice, brand.name from Product";
		Query<Object[]> qry = session.createQuery(hql, Object[].class);
		List<Object[]> list = qry.list();

		for (Object[] data : list) {
			System.out.printf("Name = %s, Price = %.2f, Brand = %s\n", 
					data[0], data[1], data[2]);
		}
	}

	static void getProductsByPage(int pageNum) {
		String hql = "from Product order by id";
		int pageSize = 10;
		int startFrom = (pageNum-1) * pageSize;
		Query<Product> qry = session.createQuery(hql, Product.class);
		
		qry.setFirstResult(startFrom);
		qry.setMaxResults(pageSize);
		
		List<Product> list = qry.list();
		for(Product p: list){
			System.out.printf("%2d - %s, Rs.%.2f (%s from %s)\n",
					p.getId(),
					p.getName(),
					p.getUnitPrice(),
					p.getCategory().getName(),
					p.getBrand().getName());
		}
	}

	static void getProductsByPriceRange(double min, double max) {
		String hql = "from Product where unitPrice between :min_price "
				+ "and :max_price order by unitPrice desc";
		Query<Product> qry = session.createQuery(hql, Product.class);
		qry.setParameter("min_price", min);
		qry.setParameter("max_price", max);
		List<Product> list = qry.list();
		for(Product p: list){
			System.out.printf("%s, Rs.%.2f (%s from %s)\n",
					p.getName(),
					p.getUnitPrice(),
					p.getCategory().getName(),
					p.getBrand().getName());
		}
	}

	static void getAllCategories() {
		String hql = "from Category";
		Query<Category> qry = session.createQuery(hql, Category.class);
		List<Category> list = qry.list(); // SQL is fired here
		for(Category c: list){
			System.out.println(c.getId() + " --> " + c.getName());
		}
	}

}











