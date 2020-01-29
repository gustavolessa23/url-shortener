package com.gustavolessa.urlshortener.services;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import com.gustavolessa.urlshortener.entities.UrlEntry;

@ApplicationScoped
public class ShorteningService {

	@Inject
	ConverterService converter;
	
	
	public String addUrl(String url) {
		UrlEntry urlEntry = new UrlEntry(url);
		urlEntry.persist();
		long id = urlEntry.id;
		String converted = converter.convertIdToCode(id);
		urlEntry.shorten = converted;
		
		return converted;
	}
	
	public String getUrl(String code) {
		long id = converter.convertCodeToId(code);
		UrlEntry entry = UrlEntry.findById(id);
		return entry.url;
	}
	
	
}
