package com.innovaee.eorder.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.innovaee.eorder.module.entity.Dish;
import com.innovaee.eorder.storage.DishStore;


@Path("/dishs")
public class DishsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Dish> getDishs() {
		List<Dish> dishs = new ArrayList<Dish>();
		dishs.addAll(DishStore.getStore().values());
		return dishs;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = DishStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newDish(@FormParam("id") String id,
			@FormParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		Dish c = new Dish(Integer.parseInt(id), name);
		DishStore.getStore().put(id, c);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_dish.jsp");
	}

	@Path("{dish}")
	public DishResource getDish(@PathParam("dish") String dish) {
		return new DishResource(uriInfo, request, dish);
	}

}