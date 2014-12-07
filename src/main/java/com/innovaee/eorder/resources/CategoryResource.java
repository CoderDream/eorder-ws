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

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.module.entity.Category;
import com.innovaee.eorder.storage.CategoryStore;
import com.innovaee.eorder.util.ParamUtil;
import com.sun.jersey.api.NotFoundException;

public class CategoryResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String categoryId;

	CategoryDao categoryDao = new CategoryDao();

	public CategoryResource(UriInfo uriInfo, Request request, String categoryId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.categoryId = categoryId;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Category getCategory() {
		Category category = categoryDao.findCategory(categoryId);
		if (category == null) {
			throw new NotFoundException("No such Category.");
		}
		return category;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putCategory(JAXBElement<Category> jaxbCategory) {
		Category c = jaxbCategory.getValue();
		return putAndGetResponse(c);
	}

	@PUT
	public Response putCategory(@Context HttpHeaders herders, byte[] in) {
		Map<String, String> params = ParamUtil.parse(new String(in));
		Category c = new Category(Integer.parseInt(params.get("categoryId")),
				params.get("categoryName"));
		return putAndGetResponse(c);
	}

	private Response putAndGetResponse(Category c) {
		Response res;
		if (CategoryStore.getStore().containsKey(c.getCategoryId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		CategoryStore.getStore().put(c.getCategoryId().toString(), c);
		return res;
	}

	@DELETE
	public void deleteCategory() {
		Category c = CategoryStore.getStore().remove(categoryId);
		if (c == null) {
			throw new NotFoundException("No such Category.");
		}
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}