/***********************************************
 * Filename       : DishResourceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resources;

import org.junit.Test;

/**
 * @Title: DishResourceTest
 * @Description: 菜品资源测试类
 * @version V1.0
 */
public class DishResourceTest extends BaseTestCase {

	/**
	 * 根据categoryId查询
	 */
	@Test
	public void getDishesById() {
		target = client.target(SERVER_URI + "/dishes/mydishes/6");
		response = target.request().get();
		String dishes = response.readEntity(String.class);
		LOGGER.debug("dishes: " + dishes);
	}

}