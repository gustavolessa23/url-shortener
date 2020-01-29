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


@Entity @Builder @AllArgsConstructor @NoArgsConstructor
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

//	public static UrlEntry findById(long id){
//        return find("id", id).firstResult();
//    }


}

//
//@Id @Getter
//@GeneratedValue(strategy = GenerationType.SEQUENCE)
//@Column(name = "id", updatable = false, nullable = false)
//private Long id;
//
//@Getter @Setter
//@Column(name = "url", nullable = false)
//private String url;
//
//public static UrlEntry findById(long id){
//    return find("id", id).firstResult();
//}
