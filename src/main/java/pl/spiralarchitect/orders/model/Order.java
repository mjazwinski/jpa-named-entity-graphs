package pl.spiralarchitect.orders.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CRT_ORDER")
@SequenceGenerator(name="ORDER_ID_GEN", sequenceName="ORDER_ID_SEQ")
public class Order {
    
    @Id
    @Column(name="ORD_ID")
    @GeneratedValue(generator="ORDER_ID_GEN", strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ORD_ORDERED_ON")
    private Date orderedOn;
    
    @OneToMany(mappedBy="order")
    private List<OrderItem> items;
    
    @ManyToOne
    @JoinColumns({    	
    	@JoinColumn(name="ORD_CUSTOMER_ID", referencedColumnName="CST_ID"),
    	@JoinColumn(name="ORD_CUSTOMER_PNR", referencedColumnName="CST_PNR")
    })
    private Customer customer;

    public Long getId() {
        return id;
    }

    public Date getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    

}
