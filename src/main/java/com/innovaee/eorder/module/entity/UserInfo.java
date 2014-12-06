package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import static javax.persistence.CascadeType.*;

@Entity
public class UserInfo implements Serializable {
	// 用户id, 不能为空, 必须唯一
	@Id
	@Column(name = "userid", unique = true, nullable = false)
	private int userid;

	// 用户名, 不能为空
	@Column(name = "userName", nullable = false)
	private String userName;

	// 性别, 不能为空
	@Column(name = "sex", nullable = false)
	private String sex;

	// 出生日期, 可以为空
	@Column(name = "birthday")
	private Timestamp birthday;

	// 地址, 不能为空
	// PERSIST 表示更新、新增UserInfo数据时会同时更新、新增Address的数据
	// REMOVE 表示从数据库删除UserInfo会同时删除Address表中对应的数据
	@OneToOne(cascade = { PERSIST, REMOVE })
	@JoinColumn(name = "addressID", nullable = false)
	private Address address;

	public UserInfo() {
	}

	public UserInfo(int userid) {
		this.setUserid(userid);
	}

	@Override
	public int hashCode() {
		return this.getUserid();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserInfo))
			return false;
		final UserInfo other = (UserInfo) object;
		return this.userid == other.userid;
	}

	@Override
	public String toString() {
		return "UserInfo[userid=" + this.userid + ", userName='" + userName + "', sex='" + sex + "', birthday=" + birthday + ", address="
				+ address + "";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
