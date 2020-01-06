package com.revature.models;

import java.io.Serializable;

public class UpdateTemplate implements Serializable{

	
	private static final long serialVersionUID = -4929974410813877406L;
	private int id;
	private double amount;
	private String description;
	private int resolver_id;
	private int status_id;
	private int type_id;
	private int author_id;
	
	
	
	public UpdateTemplate() {
		super();
	}



	public UpdateTemplate(int id,double amount, String description, int resolver_id, int status_id, int type_id,
			int author_id) {
		super();
		this.setId(id);
		this.amount = amount;
		this.description = description;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
		this.author_id = author_id;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getResolver_id() {
		return resolver_id;
	}



	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}



	public int getStatus_id() {
		return status_id;
	}



	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}



	public int getType_id() {
		return type_id;
	}



	public void setType_id(int type_id) {
		this.type_id = type_id;
	}



	public int getAuthor_id() {
		return author_id;
	}



	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
