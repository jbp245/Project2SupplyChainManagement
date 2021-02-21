package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity(name = "users")        
@Table(name = "users")
public class User {
	
	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "USER_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String role_name;
	
	@Column(unique=true, name="username")
	private String username;
	private String pass;
	private int distributor_id;
	
	
	/**
	 * DEFAULT CONSTRUCTOR
	 */
	public User() {
		super();
	}
	
	/**
	 * ID-LESS CONSTRUCTOR
	 * @param role_name
	 * @param username
	 * @param pass
	 * @param supplier_id
	 * @param distributor_id
	 */
	public User(String role_name, String username, String pass, int distributor_id) {
		super();
		this.role_name = role_name;
		this.username = username;
		this.pass = pass;
		this.distributor_id = distributor_id;
	}

	//added for UserController TestCase
	public User(int id, String role_name, String username, String pass, int distributor_id) {
		super();
		this.id = id;
		this.role_name = role_name;
		this.username = username;
		this.pass = pass;
		this.distributor_id = distributor_id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @param role_name the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role_name=" + role_name + ", username=" + username + ", pass=" + pass +"]";
	}

}
