package es.icane.metadatos.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.icane.metadatos.adapters.DateAdapter;

@XmlType
public class Reference implements Serializable {
	
		private Integer id;
	    private String title;
	    private String uri;
	    private Date created;
	    private Date lastUpdated;
	    private ResourceType resourceType;
	    
	    @XmlAttribute(required = true)
		public Integer getId() {
			return id;
		}
	    
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getUri() {
			return uri;
		}
		
		public void setUri(String uri) {
			this.uri = uri;
		}
		
		@XmlElement(name = "dateCreated")
	    @XmlJavaTypeAdapter(value = DateAdapter.class)
		public Date getCreated() {
			return created;
		}
		
		public void setCreated(Date created) {
			this.created = created;
		}
		
		@XmlElement(name = "lastUpdated")
	    @XmlJavaTypeAdapter(value = DateAdapter.class)
		public Date getLastUpdated() {
			return lastUpdated;
		}
		
		public void setLastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
		
		@Override
	    public String toString() {
	        return uri;
	    }

	    public ResourceType getResourceType() {
			return resourceType;
		}

		public void setResourceType(ResourceType resourceType) {
			this.resourceType = resourceType;
		}

		@Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Reference other = (Reference) obj;
	        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
	            return false;
	        }
	        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
	        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
	        return hash;
	    }
	    
	    
}
