package es.icane.metadatos.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class TimePeriod implements Serializable {
	
	private Integer id;
	private Integer startMonth;
	private Integer endMonth;
	private Integer startYear;
	private Integer endYear;
	private Integer quarterNumber;
	private Integer semesterNumber;
	private String timeFormat;
    private Date created;
    private Date lastUpdated;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}
	public Integer getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}
	public Integer getStartYear() {
		return startYear;
	}
	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}
	public Integer getEndYear() {
		return endYear;
	}
	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}
	public Integer getQuarterNumber() {
		return quarterNumber;
	}
	public void setQuarterNumber(Integer quarterNumber) {
		this.quarterNumber = quarterNumber;
	}
	public Integer getSemesterNumber() {
		return semesterNumber;
	}
	public void setSemesterNumber(Integer semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
	public String getTimeFormat() {
		return timeFormat;
	}
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
	 @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final TimePeriod other = (TimePeriod) obj;
	        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
	            return false;
	        }
	        if ((this.startMonth == null) ? (other.startMonth != null) : !this.startMonth.equals(other.startMonth)) {
	            return false;
	        }
		 return (this.timeFormat == null) ? other.timeFormat == null : this.timeFormat.equals(other.timeFormat);
	 }

	    @Override
	    public int hashCode() {
	        int hash = 5;
	        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
	        hash = 79 * hash + (this.startMonth != null ? this.startMonth.hashCode() : 0);
	        hash = 79 * hash + (this.timeFormat != null ? this.timeFormat.hashCode() : 0);
	        return hash;
	    }
    
}
