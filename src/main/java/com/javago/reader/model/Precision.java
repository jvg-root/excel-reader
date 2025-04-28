package com.javago.reader.model;

public enum Precision {
	VERY_LOW("Muy baja"),
	LOW("Baja"),
	MIDDLE("Media"),
	HIGH("Alta"),
	
	;
	private final String description;
	Precision(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
