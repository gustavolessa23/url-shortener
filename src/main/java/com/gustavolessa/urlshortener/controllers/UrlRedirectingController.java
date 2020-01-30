package com.gustavolessa.urlshortener.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gustavolessa.urlshortener.services.ShorteningService;

/**
 * Controller for URL redirection, from code to original URL.
 * @author Gustavo Lessa
 *
 */
@Path("/api/v1")
public class UrlRedirectingController {
	
	
	@Inject
	ShorteningService shortener;
	
	/**
	 * Redirect to original URL from shortened code.
	 * @param link
	 * @return
	 */
	@GET
	@Path("/{link}")
	@Transactional
	public Response getUrl(@PathParam("link") String link) {
		String url = shortener.getUrl(link); // get original URL from service.
		URI uri;
		try {
			uri = new URI(url); // try to create a URI.
			return Response.status(Status.TEMPORARY_REDIRECT).location(uri).build(); // if successful, redirect.
		} catch (URISyntaxException e) {
			e.printStackTrace();
			String message = "URL not found."; // if unsuccessful, send message.
			return Response.ok(message).status(Status.NOT_FOUND).build();
		}

	}

}
