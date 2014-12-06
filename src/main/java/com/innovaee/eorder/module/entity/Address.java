package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address implements Serializable {
	// 地址id, 不能为空, 必须唯一
	@Id
	@Column(name = "addressid", unique = true, nullable = false)
	private int addressid;

	// 城市, 不能为空
	@Column(name = "city", nullable = false)
	private String city;

	// 街道, 不能为空
	@Column(name = "street", nullable = false)
	private String street;

	// 邮政编码, 不能为空
	@Column(name = "zip", nullable = false)
	private String zip;

	public Address() {
	}

	public Address(int addressid) {
		this.setAddressid(addressid);
	}

	public int getAddressid() {
		return this.addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public int hashCode() {
		return this.addressid;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Address))
			return false;
		final Address other = (Address) object;
		return this.addressid == other.addressid;
	}

	@Override
	public String toString() {
		return "Address[addressid=" + getAddressid() + ", city='" + getCity() + "', street='" + getStreet() + "', zip='" + getZip() + "";
	}
}