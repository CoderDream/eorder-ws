/***********************************************
 * Filename       : CategoryResourceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: CategoryResourceTest
 * @Description: 分类资源测试类
 * @version V1.0
 */
public class CategoryResourceTest extends BaseTestCase {

	/**
	 * 查询所有菜品分类
	 */
	@Test
	public void getAllCategories() {
		target = client.target(SERVER_URI + "/categories");
		response = target.request().get();
		String categories = response.readEntity(String.class);
		LOGGER.debug("categories: " + categories);
	}

}