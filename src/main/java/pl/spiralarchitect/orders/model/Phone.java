package pl.spiralarchitect.orders.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CRT_PHONE")
@SequenceGenerator(name="PHONE_ID_GEN", sequenceName="PHONE_ID_SEQ")
public class Phone {
	
	@Id
	@Column(name="PHN_ID")
	@GeneratedValue(generator="PHONE_ID_GEN", strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="PHN_NUMBER")
    private String phoneNum;

	@Column(name="PHN_TYPE")
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PHN_CUSTOMER_ID", referencedColumnName="CST_ID"),
		@JoinColumn(name="PHN_CUSTOMER_PNR", referencedColumnName="CST_PNR")
	})
	private Customer customer;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PHN_PRIM_CUSTOMER_ID", referencedColumnName="CST_ID"),
		@JoinColumn(name="PHN_PRIM_CUSTOMER_PNR", referencedColumnName="CST_PNR")
	})
	private Customer primaryCustomer;
    
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public PhoneType getPhoneType() {
        return phoneType;
    }
    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
    
    
    
}
