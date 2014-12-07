package com.innovaee.eorder.resources;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.module.entity.Dish;
import com.innovaee.eorder.storage.DishStore;
import com.innovaee.eorder.util.ParamUtil;
import com.sun.jersey.api.NotFoundException;

public class DishResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String dish;
	DishDao dishDao = new DishDao();

	public DishResource(UriInfo uriInfo, Request request, String dish) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.dish = dish;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Dish getDish() {
		//Dish cont = DishStore.getStore().get(dish);
		Dish cont = dishDao.findDish(dish);
		
		if (cont == null)
			throw new NotFoundException("No such Dish.");
		return cont;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putDish(JAXBElement<Dish> jaxbDish) {
		Dish c = jaxbDish.getValue();
		return putAndGetResponse(c);
	}

	@PUT
	public Response putDish(@Context HttpHeaders herders, byte[] in) {
		Map<String, String> params = ParamUtil.parse(new String(in));
		Dish c = new Dish(Integer.parseInt(params.get("id")),
				params.get("name"));
		return putAndGetResponse(c);
	}

	private Response putAndGetResponse(Dish c) {
		Response res;
		if (DishStore.getStore().containsKey(c.getDishId().toString())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		DishStore.getStore().put(c.getDishId().toString(), c);
		return res;
	}

	@DELETE
	public void deleteDish() {
		Dish c = DishStore.getStore().remove(dish);
		if (c == null)
			throw new NotFoundException("No such Dish.");
	}
}