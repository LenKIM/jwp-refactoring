package kitchenpos.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MenuProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	private long quantity;

	protected MenuProduct() {
	}

	private MenuProduct(Menu menu, Product product, long quantity) {
		this.menu = menu;
		this.product = product;
		this.quantity = quantity;
	}

	public static MenuProduct create(Menu menu, Product product, long quantity) {
		return new MenuProduct(menu, product, quantity);
	}

	public BigDecimal getPrice() {
		BigDecimal decimalQuantity = BigDecimal.valueOf(this.quantity);
		return this.product
			.getPrice()
			.multiply(decimalQuantity);
	}

	public Long getSeq() {
		return seq;
	}

	public Long getProductId() {
		return this.product.getId();
	}

	public Menu getMenu() {
		return menu;
	}

	public Product getProduct() {
		return product;
	}

	public long getQuantity() {
		return quantity;
	}

}
