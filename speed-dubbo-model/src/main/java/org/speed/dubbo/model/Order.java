package org.speed.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ID id;
	
	private String orderId;
	
	private Long createTime;
	
	private BigDecimal price;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
