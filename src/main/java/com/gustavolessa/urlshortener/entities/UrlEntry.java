package com.gustavolessa.urlshortener.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Optional;

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

	public static String findByUrlOptional(String url) throws NotFoundException {
		Optional<UrlEntry> opt = UrlEntry.find("url", url).singleResultOptional();
		UrlEntry found = opt.orElseThrow(() -> new NotFoundException());
		return found.shorten;
	}

	public static String findByCodeOptional(String url) throws NotFoundException {
		Optional<UrlEntry> opt = UrlEntry.find("shorten", url).singleResultOptional();
		UrlEntry found = opt.orElseThrow(() -> new NotFoundException());
		return found.url;
	}

}
