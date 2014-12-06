package sample.hello.storage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.innovaee.eorder.module.entity.Category;

public class CategoryStore {
	private static Map<Integer, Category> store;
	private static CategoryStore instance = null;

	private CategoryStore() {
		store = new HashMap<Integer, Category>();
		initOneCategory();
	}

	public static Map<Integer, Category> getStore() {
		if (instance == null) {
			instance = new CategoryStore();
		}
		return store;
	}

	private static void initOneCategory() {
		Integer categoryId = 1;
		String categoryName = "分类1";
		String categoryPicture = "xxx/xxx.jpg";
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		Category cHuang = new Category(categoryId, categoryName,
				categoryPicture, createAt, updateAt);
		store.put(cHuang.getCategoryId(), cHuang);
	}
}