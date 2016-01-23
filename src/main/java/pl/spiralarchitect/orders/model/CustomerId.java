package pl.spiralarchitect.orders.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Embeddable
@SequenceGenerator(name="CUSTOMER_ID_GEN", sequenceName="CUSTOMER_ID_SEQ")
public class CustomerId implements Serializable {
    
    /**
     * Serial id
     */
    private static final long serialVersionUID = -3223738937190773642L;
    
    @Column(name="CST_ID")
    @GeneratedValue(generator="CUSTOMER_ID_GEN", strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="CST_PNR")
    private String pnr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pnr == null) ? 0 : pnr.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerId other = (CustomerId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pnr == null) {
            if (other.pnr != null)
                return false;
        } else if (!pnr.equals(other.pnr))
            return false;
        return true;
    }

}
