package com.projeto.upload.model;

public enum Status {
	
	SEGUNDA("Segunda"),
	TERCA("Terça"),
	QUARTA("Quarta");
	
	private String value;
	
	Status(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

