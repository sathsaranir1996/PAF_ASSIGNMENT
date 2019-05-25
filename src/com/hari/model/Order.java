package com.hari.model;

public class Order {
	private int orderid;
	private String productname;
	private int quantity;
	private String paymenttype;
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderid, String productname, int quantity, String paymenttype) {
		this.orderid = orderid;
		this.productname = productname;
		this.quantity = quantity;
		this.paymenttype = paymenttype;
		
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	
	
	

}
