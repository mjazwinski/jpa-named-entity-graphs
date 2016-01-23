package pl.spiralarchitect.orders.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRT_ORDER_ITEM")
@SequenceGenerator(name=OrderItem.CRT_ORDER_ITEM_ID_GEN, sequenceName="CRT_ORDER_ITEM_SEQ")
public class OrderItem {

	static final String CRT_ORDER_ITEM_ID_GEN = "CRT_ORDER_ITEM_ID_GEN";
	
	@Id
	@Column(name="ORI_ID")
	@GeneratedValue(generator=CRT_ORDER_ITEM_ID_GEN, strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ORI_ORDER")
	private Order order;
	
	@OneToOne
	@JoinColumn(name="ORI_PRODUCT")
	private Product product;
	
	@Column(name="ORI_QUANTITY")
	private Integer quantity;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}
	
}
