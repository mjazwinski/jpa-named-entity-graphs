package pl.spiralarchitect.orders.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRT_PRODUCT")
@SequenceGenerator(name="PRODUCT_GEN", sequenceName="PRODUCT_SEQ")
public class Product {

    @Id
    @Column(name="PRD_ID")
    @GeneratedValue(generator="PRODUCT_GEN", strategy=GenerationType.SEQUENCE)
    private Long id;
    @Column(name="PRD_NAME")
    private String name;
    
    @OneToOne(mappedBy="product")
    private OrderItem orderItems;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
