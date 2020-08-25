package com.revature.models;

import java.util.Objects;

public class User {

	private int id;
	private String username;
	private String password;
	private String Fname;
	private String Lname;
	private String email;
	private Role role;
	
	public User() {
		super();
	}

	public User(int id, String username, String password, String fname, String lname, String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		Fname = fname;
		Lname = lname;
		this.email = email;
		this.role = role;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Fname, Lname, email, id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(Fname, other.Fname) && Objects.equals(Lname, other.Lname)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", Fname=" + Fname + ", Lname="
				+ Lname + ", email=" + email + ", role=" + role + "]";
	}

}
