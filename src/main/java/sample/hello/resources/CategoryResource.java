package sample.hello.resources;

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

import sample.hello.storage.CategoryStore;
import sample.hello.util.ParamUtil;

import com.innovaee.eorder.module.entity.Category;
import com.sun.jersey.api.NotFoundException;

public class CategoryResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer categoryId;

	public CategoryResource(UriInfo uriInfo, Request request, Integer categoryId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.categoryId = categoryId;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Category getCategory() {
		Category cont = CategoryStore.getStore().get(categoryId);
		if (cont == null)
			throw new NotFoundException("No such Category.");
		return cont;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putCategory(JAXBElement<Category> jaxbCategory) {
		Category c = jaxbCategory.getValue();
		return putAndGetResponse(c);
	}

	@PUT
	public Response putCategory(@Context HttpHeaders herders, byte[] in) {
		Map<Integer, String> params = ParamUtil.parseInteger(new String(in));
		Category c = new Category(Integer.parseInt(params.get("id")),
				params.get("name"));
		return putAndGetResponse(c);
	}

	private Response putAndGetResponse(Category c) {
		Response res;
		if (CategoryStore.getStore().containsKey(c.getCategoryId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		CategoryStore.getStore().put(c.getCategoryId(), c);
		return res;
	}

	@DELETE
	public void deleteCategory() {
		Category c = CategoryStore.getStore().remove(categoryId);
		if (c == null)
			throw new NotFoundException("No such Category.");
	}
}