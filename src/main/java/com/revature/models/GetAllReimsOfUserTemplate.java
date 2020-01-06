package com.revature.models;

import java.io.Serializable;

public class GetAllReimsOfUserTemplate implements Serializable{

	
	private static final long serialVersionUID = -1892766991884020133L;
	
	private String id;

	public GetAllReimsOfUserTemplate(String id) {
		super();
		this.id = id;
	}

	public GetAllReimsOfUserTemplate() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
