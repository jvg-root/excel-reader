package com.javago.reader;

public enum Precision {
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
