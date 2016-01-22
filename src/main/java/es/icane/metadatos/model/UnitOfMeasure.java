package es.icane.metadatos.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.icane.metadatos.adapters.DateAdapter;

@XmlType
@XmlRootElement(name="unitOfMeasure")
public class UnitOfMeasure {

	 private Integer id;
	 
	    private String title;
	    private String symbol;
	 
	    private Date created;
	    private Date lastUpdated;

	    @XmlAttribute(required = true)
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    @XmlElement(name = "dateCreated")
	    @XmlJavaTypeAdapter(value = DateAdapter.class)
	    public Date getCreated() {
	        return created;
	    }

	    public void setCreated(Date created) {
	        this.created = created;
	    }

	    @XmlJavaTypeAdapter(value = DateAdapter.class)
	    public Date getLastUpdated() {
	        return lastUpdated;
	    }

	    public void setLastUpdated(Date lastUpdated) {
	        this.lastUpdated = lastUpdated;
	    }

	   

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	  

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
	        hash = 37 * hash + (this.symbol != null ? this.symbol.hashCode() : 0);
	        hash = 37 * hash + (this.title != null ? this.title.hashCode() : 0);
	       
	        return hash;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final UnitOfMeasure other = (UnitOfMeasure) obj;
	        if (this.id != other.id &&
	                (this.id == null || !this.id.equals(other.id))) {
	            return false;
	        }
	       
	        if ((this.title == null) ? (other.title != null)
	                : !this.title.equals(other.title)) {
	            return false;
	        }
	        if ((this.symbol == null) ? (other.symbol != null)
	                : !this.symbol.equals(other.symbol)) {
	            return false;
	        }
	        
	        return true;
	    }
	    
	    

	    public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		@Override
	    public String toString() {
	        return title;
	    }

	  
	
}
