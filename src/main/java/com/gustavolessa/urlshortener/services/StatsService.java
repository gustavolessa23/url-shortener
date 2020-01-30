package com.gustavolessa.urlshortener.services;

import javax.enterprise.context.ApplicationScoped;

import com.gustavolessa.urlshortener.entities.LogEntry;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Class responsible for the stats API, where new methods can be created to retrieve, for example,
 * the most accessed link.
 * @author Gustavo Lessa
 *
 */
@ApplicationScoped @NoArgsConstructor @AllArgsConstructor
public class StatsService {

	/**
	 * Log access to DB, including type "access" or "creationg"
	 * @param urlId
	 * @param type
	 * @return
	 */
	public String log(String urlId, String type) {
		LogEntry entry = new LogEntry(urlId, type);
		entry.persistAndFlush();
		return "Log saved.";
	}
	
}
