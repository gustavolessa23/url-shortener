package com.gustavolessa.urlshortener.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * URL Entry entity. According to Quarkus developers, setting the variables as public will automatically 
 * change all direct calls to accessor and mutators, and the variable will be converted to private.
 * (https://quarkus.io/guides/hibernate-orm-panache).
 * @author gustavolessa
 *
 */
@Entity @Builder
public class UrlEntry extends PanacheEntity{
	
	@Column(name = "url", nullable = false)
	public String url;
	
	@Column(name = "shorten")
	public String shorten;
	
	public UrlEntry() {
		
	}
    public UrlEntry(String url) {
    	this.url = url;
	}

}
