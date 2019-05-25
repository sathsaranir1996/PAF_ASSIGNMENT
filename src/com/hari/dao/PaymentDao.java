package com.hari.dao;

import java.util.List;


import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.hari.model.Payment;



public class PaymentDao {
private JdbcTemplate jdbcTemplate;
@Autowired
private SessionFactory session;




public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

public int registerPayment(Payment p){
	try {
		String query="INSERT INTO `payment`(`productname`,  `paymenttype`, `paymentstatus` ) VALUES ('"+p.getProductname()+"' , '"+p.getPaymenttype()+"', '"+p.getPaymentstatus()+"')";
		return jdbcTemplate.update(query);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		return -99;
	}
}



public Payment getPayment(int paymentid) {
	Payment pt;
	try {
		String productname = (String) this.jdbcTemplate.queryForObject("select productname from `payment` where paymentid = '"+paymentid+"'", String.class);
		String paymenttype = (String) this.jdbcTemplate.queryForObject("select paymenttype from `payment` where paymentid = '"+paymentid+"'", String.class);
		String paymentstatus = (String) this.jdbcTemplate.queryForObject("select paymentstatus from `payment` where paymentid = '"+paymentid+"'", String.class);
		
		pt = new Payment(paymentid, productname, paymenttype, paymentstatus);
	}
	catch(Exception e) {
		System.out.println("Error: " + e);
		pt = null;
	}
	return pt;
}

public int updatePayment(Payment p){
	
	try {
		//UPDATE `order` set paymenttype=`?`,quantity=`?` WHERE orderid='"+orderid+"';
		String query="UPDATE `payment` SET `paymenttype`= '"+p.getPaymenttype()+"' ,`paymentstatus`= '"+p.getPaymentstatus()+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

public int deactivate(int paymentid){
	try {
		String query="Delete from `payment` where paymentid='"+paymentid+"'";
		return jdbcTemplate.update(query);
	}
	catch(Exception z) {
		System.out.println(z);
		return -99;
	}
}

}