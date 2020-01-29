package com.gustavolessa.urlshortener.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
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

import com.gustavolessa.urlshortener.entities.UrlEntry;
import com.gustavolessa.urlshortener.services.ConverterService;
import com.gustavolessa.urlshortener.services.ShorteningService;

/**
 * Class to control url shortener endpoints.
 * @author Gustavo Lessa
 *
 */
@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
public class UrlShorteningController {
	
	@Inject
	ShorteningService shortener;

	
	@POST
	@Transactional
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUrl(@FormParam("url") String url) {
		String converted = shortener.addUrl(url);
		if(converted != null) {
			return Response.ok(converted).status(201).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{link}")
	public Response getUrl(@PathParam("link") String link) {
		String url = shortener.getUrl(link);
		URI uri;
		try {
			uri = new URI(url);
			return Response.status(Status.TEMPORARY_REDIRECT).location(uri).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}

	}


}