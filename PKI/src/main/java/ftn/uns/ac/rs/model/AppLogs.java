package ftn.uns.ac.rs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;




@Entity
public class AppLogs {
    @Id
    protected String id;
    @XmlElement(required = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;
    @XmlElement(required = true)
    protected String logger;
    @XmlElement(required = true)
    protected String level;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
    
}
