package com.hari.dao;

import java.util.List;


import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hari.model.Member;
import com.hari.model.Order;



public class OrderDao {
private JdbcTemplate jdbcTemplate;
@Autowired
private SessionFactory session;




public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

public int registerOrder(Order m){
	try {
		String query="INSERT INTO `order`(`productname`, `quantity`, `paymenttype` ) VALUES ('"+m.getProductname()+"' , '"+m.getQuantity()+"','"+m.getPaymenttype()+"')";
		return jdbcTemplate.update(query);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		return -99;
	}
}



public Order getOrder(int orderid) {
	Order ord;
	try {
		String productname = (String) this.jdbcTemplate.queryForObject("select productname from `order` where orderid = '"+orderid+"'", String.class);
		int quantity = Integer.parseInt((String) this.jdbcTemplate.queryForObject("select quantity from `order` where orderid = '"+orderid+"'", String.class));
		String paymenttype = (String) this.jdbcTemplate.queryForObject("select paymenttype from `order` where orderid = '"+orderid+"'", String.class);
		
		ord = new Order(orderid, productname, quantity, paymenttype);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		ord = null;
	}
	return ord;
}

public int updateOrder(Order o){
	
	try {
		//UPDATE `order` set paymenttype=`?`,quantity=`?` WHERE orderid='"+orderid+"';
		String query="UPDATE `order` SET `paymenttype`= '"+o.getPaymenttype()+"' ,`quantity`= '"+o.getQuantity()+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

public int deactivate(int orderid){
	try {
		String query="Delete from `order` where orderid='"+orderid+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

}
	
	


/**
public Order getOrd(String productname) {
	Order mem;
	try {
		int orderid = Integer.parseInt((String) this.jdbcTemplate.queryForObject("SELECT `orderid` FROM `order` WHERE `productname` = '"+productname+"'", String.class));
		//String productname = (String) this.jdbcTemplate.queryForObject("select productname from order where productname  = '"+productname+"'", String.class);
		String address	 = (String) this.jdbcTemplate.queryForObject("SELECT `address` FROM `order` WHERE `productname`  = '"+productname+"'", String.class);
	    int contactNumber = Integer.parseInt((String) this.jdbcTemplate.queryForObject("SELECT `contactNumber` FROM `order` WHERE `productname`  = '"+productname+"'", String.class));
		String quantity = (String) this.jdbcTemplate.queryForObject("SELECT `quantity` FROM `order` WHERE `productname`  = '"+productname+"'", String.class);
		mem = new Order(orderid,productname, address, contactNumber, quantity);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		mem = null;
	}
	return mem;
}

/**
public int updateEmployee(Order e){
	try {
		String query="update  `pafproject2.order` set productname='"+e.getProductname()+"',address='"+e.getAddress()+"', contactnumber='"+e.getContactNumber()+"', quantity='"+e.getQuantity()+"' where orderid='"+e.getOrderid()+"' ";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

public int deactivate(String name){
	try {
		String query="Delete from member where mname='"+name+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
} 




/*
public int deleteEmployee(Member e){
	String query="delete from employee where id='"+e.getId()+"' ";
	return jdbcTemplate.update(query);
}
*/



