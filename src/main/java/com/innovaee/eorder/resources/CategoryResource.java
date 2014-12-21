/***********************************************
 * Filename		: CategoryResource.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovaee.eorder.bean.Category;
import com.innovaee.eorder.dao.impl.CategoryDaoImpl;

/**
 * @Title: CategoryResource
 * @Description: 菜品分类资源
 * @author coderdream@gmail.com
 * @version V1.0
 */
@Path("/categories")
public class CategoryResource {
	private CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();

	/**
	 * 查询所有菜品分类
	 * 
	 * @return 所有菜品分类
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<Category>> getAllCategorys() {
		List<Category> categories = new ArrayList<Category>();
		categories = categoryDaoImpl.getAllCategories();
		Map<String, List<Category>> result = new HashMap<String, List<Category>>();
		result.put("categories", categories);
		return result;
	}

}