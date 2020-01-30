package com.gustavolessa.urlshortener.services;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import com.gustavolessa.urlshortener.entities.UrlEntry;

/**
 * Shortening service class, responsible for shortening URLs from original URLs and retrieving original URLs
 * from shortened ones.
 * @author Gustavo Lessa
 *
 */
@ApplicationScoped
public class ShorteningService {

	@Inject
	ConverterService converter;
	
	@Inject
	StatsService stats;
	
	
	/**
	 * Add new URL, checking if it is valid beforehand.
	 * @param url
	 * @return converted URL code.
	 */
	public String addUrl(String url) {
		if(ValidatorService.isValid(url)) { // check if URL is valid
			UrlEntry urlEntry = new UrlEntry(url);
			urlEntry.persistAndFlush(); // persist and send to database straight away.
			long id = urlEntry.id; // get ID.
			String converted = converter.convertIdToCode(id); // get shortened code.
			urlEntry.shorten = converted; // add to DB.
			stats.log(String.valueOf(id), "created"); // log creation to DB.
			return converted;
		} else {
			return null;
		}
	}
	
	/**
	 * Retrieve original URL from shortened code.
	 * @param code
	 * @return
	 */
	public String getUrl(String code) {
		long id = converter.convertCodeToId(code); // get original Id.
		UrlEntry entry = UrlEntry.findById(id); // get from DB original entry
		if (entry != null) {
			stats.log(String.valueOf(entry.id), "accessed"); // log access to DB
			return entry.url; // return URL
		} else {
			return null;
		}
	}
	
	
}
