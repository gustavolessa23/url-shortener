package com.gustavolessa;

import java.net.URI;
import java.net.URISyntaxException;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
public class UrlEntryResource {
	

	private final String characterMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final int charBase = characterMap.length();


	public long convertToNumber(String str){
		long num = 0;
		for(int i = 0 ; i< str.length(); i++)
			num += characterMap.indexOf(str.charAt(i)) * Math.pow(charBase , (str.length() - (i + 1)));

		return num;
	}

	
	public String convertURL(long num) {
		StringBuilder sb = new StringBuilder();

		while (num > 0){
			sb.append(characterMap.charAt((int)(num % charBase)));
			num /= charBase;
		}

		return sb.reverse().toString();
	}
	
	@POST
	@Transactional
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUrl(@FormParam("url") String url) {
		UrlEntry urlEntry = new UrlEntry(url);
		urlEntry.persist();
		long id = urlEntry.id;
		String converted = convertURL(id);
		return Response.ok(converted).status(201).build();
		
	}

	@GET
	@Path("/{link}")
	public Response getUrl(@PathParam("link") String link) {
		long id = convertToNumber(link);
		UrlEntry entry = UrlEntry.findById(id);
		String url = entry.url;
		URI uri;
		try {
			uri = new URI(url);
			return Response.status(Status.TEMPORARY_REDIRECT).location(uri).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}


}