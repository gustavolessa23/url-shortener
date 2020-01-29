package com.gustavolessa.urlshortener.services;

import java.net.URL;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidatorService {
	
	private ValidatorService() {
		
	}

    /* Returns true if url is valid */
    public static boolean isValid(String url) 
    { 
        /* Try creating a valid URL */
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
          
        // If there was an Exception 
        // while creating URL object 
        catch (Exception e) { 
            return false; 
        } 
    } 
	
}
