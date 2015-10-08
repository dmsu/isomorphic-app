package com.turbo.model;

public class Class {
	public int id;
	public int idParent;
	public String sName;

	public int getId() {
		return id;
	}

	public int getIdParent() {
		return idParent;
	}

	public String getsName() {
		return sName;
	}

	public Class(int id, int idParent, String sName) {
		this.id = id;
		this.idParent = idParent;
		this.sName = sName;
	}
}
