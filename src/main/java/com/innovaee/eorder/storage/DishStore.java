package com.innovaee.eorder.storage;

import java.util.HashMap;
import java.util.Map;

import com.innovaee.eorder.module.entity.Dish;

public class DishStore {
	private static Map<String, Dish> store;
	private static DishStore instance = null;

	private DishStore() {
		store = new HashMap<String, Dish>();
		initOneDish();
	}

	public static Map<String, Dish> getStore() {
		if (instance == null) {
			instance = new DishStore();
		}
		return store;
	}

	private static void initOneDish() {
		// Address[] addrs = { new Address("Shanghai", "Long Hua Street"), new
		// Address("Shanghai", "Dong Quan Street") };
		Dish cHuang = new Dish(Integer.parseInt("1"), "Huang Yi Ming");
		store.put(cHuang.getDishId().toString(), cHuang);
	}
}