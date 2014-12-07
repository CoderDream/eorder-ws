package com.innovaee.eorder.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.innovaee.eorder.module.util.TimestampAdapter;

@XmlRootElement
public class Category implements Serializable {

	// 用户id, 不能为空, 必须唯一
	private Integer categoryId;

	// 分类名称
	private String categoryName;

	// 分类图片地址
	private String categoryPicture;

	// 创建时间
	private Timestamp createAt;

	// 更新时间
	private Timestamp updateAt;

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(Integer categoryId) {
		this.setCategoryId(categoryId);
	}

	public Category(Integer categoryId, String categoryName) {
		this.setCategoryId(categoryId);
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		return this.getCategoryId();
	}

	public Category(Integer categoryId, String categoryName,
			String categoryPicture, Timestamp createAt, Timestamp updateAt) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryPicture = categoryPicture;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Category))
			return false;
		final Category other = (Category) object;
		return this.categoryId == other.categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryPicture() {
		return categoryPicture;
	}

	public void setCategoryPicture(String categoryPicture) {
		this.categoryPicture = categoryPicture;
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

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryPicture=" + categoryPicture
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}

}