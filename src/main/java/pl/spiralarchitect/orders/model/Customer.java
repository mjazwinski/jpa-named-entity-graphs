package pl.spiralarchitect.orders.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CRT_CUSTOMER")
@NamedEntityGraphs({
	@NamedEntityGraph(name=Customer.CUSTOMERS_WITH_PHONES,
		attributeNodes={
				@NamedAttributeNode("id"),
				@NamedAttributeNode("firstName"),
				@NamedAttributeNode("lastName"),
				@NamedAttributeNode("email"),
				@NamedAttributeNode("phoneNums")
		}),
	@NamedEntityGraph(name=Customer.CUSTOMER_DATA,
		attributeNodes={
				@NamedAttributeNode("id"),
				@NamedAttributeNode("firstName"),
				@NamedAttributeNode("lastName")
		}
	)
})
public class Customer {
	
	public static final String CUSTOMERS_WITH_PHONES = "CRT.CUSTOMERS_WITH_PHONES";
	public static final String CUSTOMER_DATA = "CRT.CUSTOMER_DATA";
	
    @EmbeddedId
    private CustomerId id;
    @Column(name="CST_FIRST_NAME")
    private String firstName;
    @Column(name="CST_LAST_NAME")
    private String lastName;
    @Column(name="CST_EMAIL")
    private String email;
    
    @OneToMany(mappedBy="customer")
    private List<Phone> phoneNums;
    
    @OneToOne(mappedBy="primaryCustomer")
    private Phone phone;
    
    @Embedded
    @AttributeOverrides({
    	@AttributeOverride(name="postalCode", column=@Column(name="CST_POSTAL_CODE")),
    	@AttributeOverride(name="cityName", column=@Column(name="CST_CITY")),
    	@AttributeOverride(name="streetName", column=@Column(name="CST_STREET")),
    	@AttributeOverride(name="houseNumber", column=@Column(name="CST_HOUSE_NUMBER")),
    	@AttributeOverride(name="flatNumber", column=@Column(name="CST_FLAT_NUBMER"))
    })
    private Address address;
    
    @OneToMany(mappedBy="customer")
    private List<Order> orders;

    public CustomerId getId() {
        return id;
    }
    
    public void setId(CustomerId id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(List<Phone> phoneNums) {
        this.phoneNums = phoneNums;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNum=" + phoneNums + ", address="
                + address + ", orders=" + orders + "]";
    }

}
