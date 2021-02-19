package com.revature.beans;

import javax.persistence.Column;

public class UserDistributor {
	
	private String role_name;
	private String username;
	private String pass;


	private String name;

	private String address;
	
	private String phone_number;
	private int distributor_id;
	
	public UserDistributor() {
		
	}
	public UserDistributor(String role_name, String username, String pass,String name, String address, String phone_number) {
		this.role_name = role_name;
		this.username = username;
		this.pass = pass;
		//this.distributor_id = distributor_id;
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
	}
	@Override
	public String toString() {
		return "UserDistributor [role_name=" + role_name + ", username=" + username + ", pass=" + pass
				+ ", distributor_id=" + distributor_id + ", name=" + name + ", address=" + address + ", phone_number="
				+ phone_number + "]";
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	

}
