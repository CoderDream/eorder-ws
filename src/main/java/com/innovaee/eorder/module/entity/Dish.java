package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.innovaee.eorder.module.util.TimestampAdapter;

@Entity
@Table(name = "t_dish")
@XmlRootElement
public class Dish extends BaseEntity {

	// dish_id
	// dish_name
	// dish_picture
	// price
	// on_sell
	// misc
	// create_at
	// update_at

	@Override
	public Serializable getPK() {
		return dishId;
	}

	// 用户id, 不能为空, 必须唯一
	@Id
	@Column(name = "dish_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dishId;

	// 分类名称
	@Column(name = "dish_name")
	private String dishName;

	// 分类图片地址
	@Column(name = "dish_picture")
	private String dishPicture;

	@Column(name = "picture")
	private String price;

	@Column(name = "on_sell")
	private Boolean onSell;

	@Column(name = "misc")
	private String misc;

	// 创建时间
	@Column(name = "create_at")
	private Timestamp createAt;

	// 更新时间
	@Column(name = "update_at")
	private Timestamp updateAt;

	public Dish() {
	}

	public Dish(Integer dishId) {
		this.setDishId(dishId);
	}

	public Dish(Integer dishId, String dishName) {
		this.setDishId(dishId);
		this.dishName = dishName;
	}

	@Override
	public int hashCode() {
		return this.getDishId();
	}

	public Dish(Integer dishId, String dishName, String dishPicture,
			Timestamp createAt, Timestamp updateAt) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPicture = dishPicture;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Dish))
			return false;
		final Dish other = (Dish) object;
		return this.dishId == other.dishId;
	}

	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishPicture() {
		return dishPicture;
	}

	public void setDishPicture(String dishPicture) {
		this.dishPicture = dishPicture;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getOnSell() {
		return onSell;
	}

	public void setOnSell(Boolean onSell) {
		this.onSell = onSell;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

}