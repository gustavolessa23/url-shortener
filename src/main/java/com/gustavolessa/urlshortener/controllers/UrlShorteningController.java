package com.gustavolessa.urlshortener.controllers;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gustavolessa.urlshortener.services.ShorteningService;

/**
 * Class to control url shortener endpoint.
 * @author Gustavo Lessa
 *
 */
@Path("/api/v1")
public class UrlShorteningController {
	
	@Inject
	ShorteningService shortener;

	/**
	 * Add new URL to the DB, using a POST request, adding url as form urlencoded bode attribute.
	 * @param url
	 * @return
	 */
	@POST
	@Transactional
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUrl(@FormParam("url") String url) {
		String converted = shortener.addUrl(url);
		if(converted != null) {
			String message = "Success! The URL can be accessed by /"+converted;
			return Response.ok(message).status(Status.CREATED).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}


}