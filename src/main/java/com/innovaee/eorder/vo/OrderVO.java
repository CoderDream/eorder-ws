/***********************************************
 * Filename		: OrderVO.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: OrderVO
 * @Description: 订单值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class OrderVO implements Serializable {

	/** 订单ID */
	private Integer orderId;

	/** 订单时间 */
	private Timestamp createAt;

	/** 订单总价 */
	private Float totalPrice;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", createAt=" + createAt
				+ ", totalPrice=" + totalPrice + "]";
	}

}