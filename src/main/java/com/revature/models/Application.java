package com.revature.models;

import java.util.Objects;

public class Application {

	private int id;
	private User owner;
	private String appStatus;
	
	public Application() {
		super();
	}

	public Application(int id, User owner, String appStatus) {
		super();
		this.id = id;
		this.owner = owner;
		this.appStatus = appStatus;
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

	
	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appStatus, id, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		return Objects.equals(appStatus, other.appStatus) && id == other.id && Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", owner=" + owner + ", appStatus=" + appStatus + "]";
	}

}
