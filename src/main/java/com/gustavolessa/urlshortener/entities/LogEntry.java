package com.gustavolessa.urlshortener.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
/**
 * Class to log creation and access of URLs to another DB table.
 * @author Gustavo Lessa
 *
 */
@Entity @Builder @AllArgsConstructor
public class LogEntry extends PanacheEntityBase{
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	
	@NotNull
	@Column(name = "urlId", nullable = false)
	private String urlId;
	
	@NotNull
	@Column(name = "type", nullable = false)
	private String type; // "creation" or "access"
	
	@NotNull
	@Column(name = "timestamp", nullable = false)
	private String timestamp;
	
	public LogEntry() {
    	this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString();;
	}
    public LogEntry(String urlId, String type) {
    	this.urlId = urlId;
    	this.type = type;
    	this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString();;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the urlId
	 */
	public String getUrlId() {
		return urlId;
	}
	/**
	 * @param urlId the urlId to set
	 */
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

    
    
}
