package com.gustavolessa.urlshortener.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
/**
 * Class to log creation and access of URLs to another DB table.
 * @author Gustavo Lessa
 *
 */
@Entity @Builder @NoArgsConstructor @AllArgsConstructor
public class LogEntry extends PanacheEntity{
	
	@NotNull
	@Column(name = "urlId", nullable = false)
	public String urlId;
	
	@NotNull
	@Column(name = "type", nullable = false)
	public String type; // "creation" or "access"
	
	@NotNull
	@Column(name = "timestamp", nullable = false)
	public String timestamp;
	
	public LogEntry() {
    	this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString();;
	}
    public LogEntry(String urlId, String type) {
    	this.urlId = urlId;
    	this.type = type;
    	this.timestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString();;
	}

}
