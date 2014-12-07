package com.innovaee.eorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.module.entity.Dish;
import com.innovaee.eorder.util.DBUtil;

/**
 * @author CoderDream
 * @date 2014年10月15日
 * 
 */
public class DishDao {

	/**
	 * 增加分类
	 * 
	 * @param dish
	 * @return
	 */
	public int addDish(Dish dish) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into t_dish(dish_name,dish_picture,create_at) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dish.getDishName());
			ps.setString(2, dish.getDishPicture());
			ps.setTimestamp(3, dish.getCreateAt());
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
	 * @param dish
	 * @return
	 */
	public int updateDish(Dish dish) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_dish set dish_name=?,dish_picture=?,create_at=?,update_at=? where dish_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dish.getDishName());
			ps.setString(2, dish.getDishPicture());
			ps.setTimestamp(3, dish.getCreateAt());
			ps.setTimestamp(4, dish.getUpdateAt());
			ps.setInt(5, dish.getDishId());
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
	public Dish findDish(String dishId) {
		Dish rtnDish = null;
		List<Dish> dishList = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select dish_id,dish_name,dish_picture,create_at,update_at from t_dish where dish_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(dishId));
			rs = ps.executeQuery();
			// 调用记录集对象的next方法，移动指针，如果到达了EOF返回false
			dishList = new ArrayList<Dish>();
			Dish dish = null;
			while (rs.next()) {
				// 学员类对象
				dish = new Dish();
				// 为学员对象属性赋值
				dish.setDishId(rs.getInt(1));
				dish.setDishName(rs.getString(2));
				dish.setDishPicture(rs.getString(3));
				dish.setCreateAt(rs.getTimestamp(4));
				dish.setUpdateAt(rs.getTimestamp(5));

				// 为集合类添加对象
				dishList.add(dish);
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

		if (null != dishList && 0 < dishList.size()) {
			rtnDish = dishList.get(0);
		}
		return rtnDish;
	}

	/**
	 * 查找所有的分类
	 * 
	 * @return
	 */
	public List<Dish> findAllDishs() {
		List<Dish> dishList = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select dish_id,dish_name,dish_picture,create_at,update_at from t_dish";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			// 调用记录集对象的next方法，移动指针，如果到达了EOF返回false
			dishList = new ArrayList<Dish>();
			Dish dish = null;
			while (rs.next()) {
				// 学员类对象
				dish = new Dish();
				// 为学员对象属性赋值
				dish.setDishId(rs.getInt(1));
				dish.setDishName(rs.getString(2));
				dish.setDishPicture(rs.getString(3));
				dish.setCreateAt(rs.getTimestamp(4));
				dish.setUpdateAt(rs.getTimestamp(5));

				// 为集合类添加对象
				dishList.add(dish);
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

		return dishList;
	}

	/**
	 * 删除分类
	 * 
	 * @param dishId
	 *            分类ID
	 * @return
	 */
	public int deleteDish(String dishId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from t_dish where dish_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dishId);
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