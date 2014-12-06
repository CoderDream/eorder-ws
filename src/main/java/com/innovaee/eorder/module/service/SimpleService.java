package com.innovaee.eorder.module.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.innovaee.eorder.module.entity.Address;
import com.innovaee.eorder.module.entity.UserInfo;

public class SimpleService {

	/**
	 * 删除用户id=6的数据
	 */
	public static void delete() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();
		// 找不到数据的话这里会抛异常
		UserInfo info = em.find(UserInfo.class, 6);
		try {
			em.getTransaction().begin();
			em.remove(info);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 修改用户id=6的数据
	 */
	public static void update() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();
		// 找不到数据的话这里会抛异常
		UserInfo info = em.find(UserInfo.class, 6);
		info.setUserName("哈哈");
		info.getAddress().setStreet("坂田2");
		try {
			em.getTransaction().begin();
			// 自动将info更新到数据库
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 查询所有用户数据
	 */
	public static void query() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		long s = System.currentTimeMillis();
		// 数据库连接失败这里会抛出异常
		final EntityManager em = emf.createEntityManager();
		long e = System.currentTimeMillis();
		System.out.println("连接数据库耗时: " + (e - s) + "毫秒");
		// 获取数据
		@SuppressWarnings("unchecked")
		List<UserInfo> list = em.createQuery("SELECT a FROM UserInfo a")
				.getResultList();
		int i = 0;
		for (UserInfo info : list) {
			System.out.println("第" + (++i) + "个值为: " + info);
		}
		em.close();
	}

	/**
	 * 创建用户id=6的一条数据, 地址id=6
	 */
	public static void create() {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("piscesPU");
		final EntityManager em = emf.createEntityManager();

		UserInfo info = new UserInfo(6);
		info.setSex("male");
		info.setUserName("张某某");
		info.setBirthday(new java.sql.Timestamp(System.currentTimeMillis()));
		Address naddr = new Address(6);
		naddr.setCity("深圳");
		naddr.setStreet("坂田");
		naddr.setZip("518000");
		info.setAddress(naddr);

		try {
			em.getTransaction().begin();
			em.persist(info);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Throwable {
		SimpleService.query();
		SimpleService.create();
		System.out.println("新增一条数据后进行查询");
		SimpleService.query();
		SimpleService.update();
		System.out.println("修改一条数据后进行查询");
		SimpleService.query();
		SimpleService.delete();
		System.out.println("删除一条数据后进行查询");
		SimpleService.query();
	}
}