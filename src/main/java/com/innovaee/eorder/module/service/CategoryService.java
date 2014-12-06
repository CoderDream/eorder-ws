package com.innovaee.eorder.module.service;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.innovaee.eorder.module.entity.Category;

public class CategoryService extends BaseService {

	/**
	 * 删除用户id=6的数据
	 */
	public void delete() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();
		// 找不到数据的话这里会抛异常
		Category category = em.find(Category.class, 6);
		try {
			em.getTransaction().begin();
			em.remove(category);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 修改用户id=6的数据
	 */
	public void update() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();
		// 找不到数据的话这里会抛异常
		Category category = em.find(Category.class, 6);
		category.setCategoryName("哈哈");
		try {
			em.getTransaction().begin();
			// 自动将category更新到数据库
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 查询所有用户数据
	 */
	public void query() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		long s = System.currentTimeMillis();
		// 数据库连接失败这里会抛出异常
		final EntityManager em = emf.createEntityManager();
		long e = System.currentTimeMillis();
		System.out.println("连接数据库耗时: " + (e - s) + "毫秒");
		// 获取数据
		@SuppressWarnings("unchecked")
		List<Category> list = em.createQuery("SELECT a FROM Category a")
				.getResultList();
		int i = 0;
		for (Category category : list) {
			System.out.println("第" + (++i) + "个值为: " + category);
		}
		em.close();
	}

	/**
	 * 创建用户id=6的一条数据, 地址id=6
	 */
	public void create() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();

		Category category = new Category(6);
		category.setCategoryName("张某某");
		//category.setCreateAt(new Timestamp(System.currentTimeMillis()));
		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Throwable {
		CategoryService categoryService = new CategoryService();

		categoryService.query();
		categoryService.create();
		System.out.println("新增一条数据后进行查询");
		categoryService.query();
		categoryService.update();
		System.out.println("修改一条数据后进行查询");
		categoryService.query();
		categoryService.delete();
		System.out.println("删除一条数据后进行查询");
		categoryService.query();
	}
}