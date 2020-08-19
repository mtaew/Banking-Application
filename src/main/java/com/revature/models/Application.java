package com.revature.models;

import java.util.Objects;

public class Application {

	private int id;
	private User owner;
	
	public Application() {
		super();
	}

	public Application(int id, User owner) {
		super();
		this.id = id;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Application)) {
			return false;
		}
		Application other = (Application) obj;
		return id == other.id && Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", owner=" + owner + "]";
	}
}
