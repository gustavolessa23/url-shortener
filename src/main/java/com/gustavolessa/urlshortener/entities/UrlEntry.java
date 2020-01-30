package com.gustavolessa.urlshortener.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
/**
 * URL Entry entity. According to Quarkus developers, setting the variables as public will automatically 
 * change all direct calls to accessor and mutators, and the variable will be converted to private.
 * (https://quarkus.io/guides/hibernate-orm-panache).
 * @author gustavolessa
 *
 */
@Entity @Builder @AllArgsConstructor
public class UrlEntry extends PanacheEntity{

	@NotNull
	@Column(name = "url", nullable = false)
	public String url;
	
	@NotNull
	@Column(name = "shorten")
	public String shorten;
	
	public UrlEntry() {
		
	}
    public UrlEntry(String url) {
    	this.url = url;
	}

}
