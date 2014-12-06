package com.innovaee.eorder.module.service;

import org.junit.Test;

public class CategoryServiceTest {

	/**
	 * 创建用户id=6的一条数据, 地址id=6
	 */
	@Test
	public void create() {
		CategoryService categoryService = new CategoryService();
		categoryService.create();
	}

	/**
	 * 修改用户id=6的数据
	 */
	@Test
	public void update() {
		CategoryService categoryService = new CategoryService();
		categoryService.create();
		categoryService.update();
	}

	/**
	 * 查询所有用户数据
	 */
	@Test
	public void query() {
		CategoryService categoryService = new CategoryService();
		categoryService.query();
	}

	/**
	 * 删除用户id=6的数据
	 */
	@Test
	public void delete() {
		CategoryService categoryService = new CategoryService();
		categoryService.delete();
	}

}
