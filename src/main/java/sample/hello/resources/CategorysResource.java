package sample.hello.resources;

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

import sample.hello.storage.CategoryStore;

import com.innovaee.eorder.module.entity.Category;

@Path("/categorys")
public class CategorysResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Category> getCategorys() {
		List<Category> categorys = new ArrayList<Category>();
		categorys.addAll(CategoryStore.getStore().values());
		return categorys;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = CategoryStore.getStore().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newCategory(@FormParam("categoryId") Integer categoryId,
			@FormParam("categoryName") String categoryName,
			@Context HttpServletResponse servletResponse) throws IOException {
		Category c = new Category(categoryId, categoryName);
		CategoryStore.getStore().put(categoryId, c);

		URI uri = uriInfo.getAbsolutePathBuilder().path(categoryId.toString()).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../pages/new_category.jsp");
	}

	@Path("{category}")
	public CategoryResource getCategory(@PathParam("categoryId") Integer categoryId) {
		return new CategoryResource(uriInfo, request, categoryId);
	}

}