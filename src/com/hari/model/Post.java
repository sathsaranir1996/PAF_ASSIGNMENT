package com.hari.model;

public class Post {
	private int postid;
	private String profileepic;
	private String productname;
	private int price;
	private int contactnumber;
	private String category;
	private String dec;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(int postid, String profilepic, String productname, int price, int contactnumber,String category,String dec) {
		this.postid = postid;
		this.profileepic = profilepic;
		this.productname = productname;
		this.price = price;
		this.contactnumber = contactnumber;
		this.category = category;
		this.dec=dec;
		
		
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getProfileepic() {
		return profileepic;
	}
	public void setProfileepic(String profileepic) {
		this.profileepic = profileepic;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(int contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}

	
	
	
}