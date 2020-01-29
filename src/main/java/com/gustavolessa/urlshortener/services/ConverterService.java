package com.gustavolessa.urlshortener.services;

import javax.enterprise.context.ApplicationScoped;

/**
 * Class responsible for converting ID to code (for shorten URL) and vice-versa.
 * @author Gustavo Lessa
 *
 */
@ApplicationScoped
public class ConverterService {

	// Base62 for code creation. It can be changed by modifying this variable
	private final String characterMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final int charBase = characterMap.length();


	/**
	 * Convert a shorten URL's code to database ID.
	 * Based on answers from StackOverflow (https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener).
	 * It converts a base62 to a base 10 number.
	 * @param code from shorten URL
	 * @return database ID.
	 */
	public long convertCodeToId(String str){
		long num = 0;
		for(int i = 0 ; i< str.length(); i++)
			num = num * charBase + characterMap.indexOf(str.charAt(i) + 1);

		return num;
	}

	/**
	 * Convert a database ID to a shorten URL's code.
	 * @param ID from database
	 * @return base62 code
	 */
	public String convertIdToCode(long num) {
		StringBuilder sb = new StringBuilder();

		while (num > 0){
			sb.append(characterMap.charAt((int)(num % charBase)-1));
			num /= charBase;
		}

		return sb.reverse().toString();
	}
}
