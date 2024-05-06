package com.keltenfalez.oreillyspring.enums;

public enum TenderType {

	CASH("cash"),
	CREDIT("credit");

	private String theType;

	TenderType(String theType) {
		this.theType = theType;
	}

	public String getTheType() {
		return theType;
	}
}
