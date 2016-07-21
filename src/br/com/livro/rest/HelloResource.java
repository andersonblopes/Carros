package br.com.livro.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.w3c.dom.stylesheets.MediaList;

import br.com.livro.domain.Response;

@Path("/hello")
public class HelloResource {

	/*
	 * @GET public String get() { return "HTTP GET"; }
	 * 
	 * @POST public String post() { return "HTTP POST"; }
	 * 
	 * @PUT public String pu() { return "HTTP PUT"; }
	 * 
	 * @DELETE public String delete() { return "HTTP DELETE"; }
	 */

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public String helloHTML() {
		return "<b>Olá, mundo HTML!</b>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloTextPlain() {
		return "Olá, mundo texto!";
	}

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public Response helloXML() {
		return Response.OK("Olá, mundo XML!");
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloJSON() {
		return Response.OK("Olá, mundo JSON!");
	}
}
