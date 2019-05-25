package com.hari.model;

public class Payment {
	
		private int paymentid;
		private String productname;
		private String paymentstatus;
		private String paymenttype;
		
		
		public Payment() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Payment(int paymentid, String productname, String paymenttype, String paymentstatus) {
			this.paymentid = paymentid;
			this.productname = productname;
			this.paymentstatus = paymentstatus;
			this.paymenttype = paymenttype;
			
		}
		public int getPaymentid() {
			return paymentid;
		}
		public void setPaymentid(int paymentid) {
			this.paymentid = paymentid;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getPaymentstatus() {
			return paymentstatus;
		}
		public void setPaymentstatus(String paymentstatus) {
			this.paymentstatus = paymentstatus;
		}
		public String getPaymenttype() {
			return paymenttype;
		}
		public void setPaymenttype(String paymenttype) {
			this.paymenttype = paymenttype;
		}

		
		
}
