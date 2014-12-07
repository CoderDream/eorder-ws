package com.innovaee.eorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.module.entity.Category;
import com.innovaee.eorder.util.DBUtil;

/**
 * @author CoderDream
 * @date 2014年10月15日
 * 
 */
public class CategoryDao {

	/**
	 * 增加分类
	 * 
	 * @param category
	 * @return
	 */
	public int addCategory(Category category) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_category(category_name,category_picture,create_at) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryPicture());
			ps.setTimestamp(3, category.getCreateAt());
			result = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 修改分类
	 * 
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_category set category_name=?,category_picture=?,create_at=?,update_at=? where category_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryPicture());
			ps.setTimestamp(3, category.getCreateAt());
			ps.setTimestamp(4, category.getUpdateAt());
			ps.setInt(5, category.getCategoryId());
			result = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 查找分类
	 * 
	 * @param id
	 *            分类ID
	 * @return
	 */
	public Category findCategory(String categoryId) {
		Category rtnCategory = null;
		List<Category> categoryList = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select category_id,category_name,category_picture,create_at,update_at from t_category where category_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(categoryId));
			rs = ps.executeQuery();
			// 调用记录集对象的next方法，移动指针，如果到达了EOF返回false
			categoryList = new ArrayList<Category>();
			Category category = null;
			while (rs.next()) {
				// 学员类对象
				category = new Category();
				// 为学员对象属性赋值
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCategoryPicture(rs.getString(3));
				category.setCreateAt(rs.getTimestamp(4));
				category.setUpdateAt(rs.getTimestamp(5));

				// 为集合类添加对象
				categoryList.add(category);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		if (null != categoryList && 0 < categoryList.size()) {
			rtnCategory = categoryList.get(0);
		}
		return rtnCategory;
	}

	/**
	 * 查找所有的分类
	 * 
	 * @return
	 */
	public List<Category> findAllCategorys() {
		List<Category> categoryList = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select category_id,category_name,category_picture,create_at,update_at from t_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			// 调用记录集对象的next方法，移动指针，如果到达了EOF返回false
			categoryList = new ArrayList<Category>();
			Category category = null;
			while (rs.next()) {
				// 学员类对象
				category = new Category();
				// 为学员对象属性赋值
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setCategoryPicture(rs.getString(3));
				category.setCreateAt(rs.getTimestamp(4));
				category.setUpdateAt(rs.getTimestamp(5));

				// 为集合类添加对象
				categoryList.add(category);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return categoryList;
	}

	/**
	 * 删除分类
	 * 
	 * @param categoryId
	 *            分类ID
	 * @return
	 */
	public int deleteCategory(String categoryId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_category where category_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoryId);
			result = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}