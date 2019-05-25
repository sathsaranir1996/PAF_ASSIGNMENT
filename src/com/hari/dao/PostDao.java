package com.hari.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hari.model.Order;
import com.hari.model.Post;

public class PostDao {
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SessionFactory session;




	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int registerPost(Post p){
		try {
			String query="INSERT INTO `post`(`profilepic`, `productname`, `price`, `contactnumber`, `category`, `dec` ) VALUES ('"+p.getProfileepic()+"' , '"+p.getProductname()+"','"+p.getPrice()+"', '"+p.getContactnumber()+"', '"+p.getCategory()+"','"+p.getDec()+"')";
			return jdbcTemplate.update(query);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
			return -99;
		}
	}

	public Post getPost(int postid) {
		Post pst;
		try {
			String profilepic = (String) this.jdbcTemplate.queryForObject("select profilepic from `post` where postid = '"+postid+"'", String.class);
			String productname = (String) this.jdbcTemplate.queryForObject("select productname from `post` where postid = '"+postid+"'", String.class);
			int price = Integer.parseInt((String) this.jdbcTemplate.queryForObject("select price from `post` where postid = '"+postid+"'", String.class));
			int contactnumber = Integer.parseInt((String) this.jdbcTemplate.queryForObject("select contactnumber from `post` where postid = '"+postid+"'", String.class));
			String category = (String) this.jdbcTemplate.queryForObject("select category from `post` where postid = '"+postid+"'", String.class);
			String dec = (String) this.jdbcTemplate.queryForObject("select `dec` from `post` where postid = '"+postid+"'", String.class);
			
			pst = new Post(postid, profilepic,  productname, price, contactnumber,category,dec);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
			pst = null;
		}
		return pst;
	}
	
	public int updatePost(Post p){
		
		try {
			//UPDATE `order` set paymenttype=`?`,quantity=`?` WHERE orderid='"+orderid+"';
			String query="UPDATE `post` SET `price`= '"+p.getPrice()+"' ,`contactnumber`= '"+p.getContactnumber()+"'";
			return jdbcTemplate.update(query);
		}
		catch(Exception z) {
			System.out.println(z);
			return -99;
		}
	}

	public int deactivate(int postid){
		try {
			String query="Delete from `post` where postid='"+postid+"'";
			return jdbcTemplate.update(query);
		}
		catch(Exception z) {
			System.out.println(z);
			return -99;
		}
	}
	
	
}
