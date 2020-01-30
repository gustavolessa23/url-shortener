package com.gustavolessa.urlshortener.services;

import java.net.URL;

import javax.enterprise.context.ApplicationScoped;

/**
 * Helper class to validate URLs.
 * @author gustavolessa
 *
 */
@ApplicationScoped
public class ValidatorService {

	private ValidatorService() {

	}

	/**
	 * Check if URL is valid
	 * @param url to be tested
	 * @return boolean
	 */
	public static boolean isValid(String url) { 

		try { 
			new URL(url).toURI(); // try creating a URI, that only happens if URL is valid.
			return true; // success
		} catch (Exception e) { 
			return false; // fail
		} 
	} 

}
