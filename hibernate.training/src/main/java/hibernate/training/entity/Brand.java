package hibernate.training.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	// one brand --> many products
	// mappedBy="brands" --> "brands" is the name of the variable
	// representing the inverse side of this relationship
	// this relationship --> one brand, many products
	// inverse relationship --> many products, one brand (in Product.java)
	@OneToMany(mappedBy = "brand", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @JoinColumn(name = "brand_id")
	private Set<Product> products = new HashSet<Product>();
	// Hibernate will override this 

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}
	
	public void addProduct(Product product){
		this.products.add(product);
		product.setBrand(this);
	}

}
