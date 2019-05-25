package com.hari.model;

public class Shipping {

	private int shipid;
	private String shipname;
    private String shipaddress;
	private int shipnum;
	private String shippic;
	
	public Shipping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shipping(int shipid, String shippic, String shipname, String shipaddress, int shipnum) {
		this.shipid=shipid;
		this.shippic=shippic;
		this.shipname=shipname;
		this.shipaddress=shipaddress;
		this.shipnum=shipnum;
		
		
		
	}
	public int getShipid() {
		return shipid;
	}
	public void setShipid(int shipid) {
		this.shipid = shipid;
	}
	public String getShipname() {
		return shipname;
	}
	public void setShipname(String shipname) {
		this.shipname = shipname;
	}
	public String getShipaddress() {
		return shipaddress;
	}
	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}
	public int getShipnum() {
		return shipnum;
	}
	public void setShipnum(int shipnum) {
		this.shipnum = shipnum;
	}
	public String getShippic() {
		return shippic;
	}
	public void setShippic(String shippic) {
		this.shippic = shippic;
	}
	
	
	
}
