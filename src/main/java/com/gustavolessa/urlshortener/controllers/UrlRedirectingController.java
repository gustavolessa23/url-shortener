package com.gustavolessa.urlshortener.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gustavolessa.urlshortener.services.ShorteningService;
import com.gustavolessa.urlshortener.services.StatsService;

/**
 * Controller for URL redirection, from code to original URL.
 * @author Gustavo Lessa
 *
 */
@Path("/api/v1")
public class UrlRedirectingController {
	
	
	@Inject
	private ShorteningService shortener;

	@Inject
	private StatsService stats;
	
//	/**
//	 * Redirect to original URL from shortened code. LEGACY CODE, NOT USED
//	 * @param link
//	 * @return
//	 */
//	@GET
//	@Path("/{link}")
//	public Response getUrl(@PathParam("link") String link) {
//		String url = shortener.getUrl(link); // get original URL from service.
//		URI uri;
//		try {
//			uri = new URI(url); // try to create a URI.
//			return Response.status(Status.MOVED_PERMANENTLY).location(uri).build(); // if successful, redirect.
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//			String message = "URL not found."; // if unsuccessful, send message.
//			return Response.ok(message).status(Status.NOT_FOUND).build();
//		}
//
//	}

	/**
	 * Redirect to original URL from shortened code.
	 * @param code
	 * @return
	 */
	@GET
	@Path("/{code}")
	public Response getUrl(@PathParam("code") String code) {
		try{
			String url = shortener.getUrl2(code); // get original URL from service.
			stats.log(code, "accessed");
			URI uri = new URI(url); // try to create a URI.
			return Response.status(Status.MOVED_PERMANENTLY).location(uri).build(); // if successful, redirect.
		} catch (NotFoundException e) {
			String message = "URL not found."; // if unsuccessful, send message.
			stats.log(code, "not found");
			return Response.ok(message).status(Status.NOT_FOUND).build();
		}catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

}
