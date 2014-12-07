package com.innovaee.eorder.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovaee.eorder.bean.Category;
import com.innovaee.eorder.dao.impl.CategoryDaoImpl;

/**
 * 用户资源
 * 
 * @author waylau.com 2014-3-19
 */
@Path("/categorys")
public class CategoryResource {
	private CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();

	/**
	 * 增加
	 * 
	 * @param category
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createCategory(Category category) {
		categoryDaoImpl.createCategory(category);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	public void deleteCategory(@PathParam("id") String id) {
		categoryDaoImpl.deleteCategoryById(id);
	}

	/**
	 * 修改
	 * 
	 * @param category
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateCategory(Category category) {
		categoryDaoImpl.updateCategory(category);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Category getCategoryById(@PathParam("id") String id) {
		Category u = categoryDaoImpl.getCategoryById(id);
		return u;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Category> getAllCategorys() {
		List<Category> categorys = new ArrayList<Category>();
		categorys = categoryDaoImpl.getAllCategorys();
		return categorys;
	}

}