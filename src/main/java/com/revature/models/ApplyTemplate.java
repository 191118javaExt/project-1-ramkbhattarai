package com.revature.models;

import java.io.Serializable;


public class ApplyTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double amount;
	private String description;
	
	//private String receipt;
	
	
	private int status_id;
	private String type_id;
	private int author_id;
	
	
	public ApplyTemplate() {
		super();
	}
	public ApplyTemplate(double amount, String description, int status_id, String type_id, int author_id) {
		super();
		this.amount = amount;
		this.description = description;
		//this.receipt = receipt;
		this.status_id = status_id;
		this.type_id = type_id;
		this.setAuthor_id(author_id);
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
//	public String getReceipt() {
//		return receipt;
//	}
//	public void setReceipt(String receipt) {
//		this.receipt = receipt;
//	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	@Override
	public String toString() {
		return "ApplyTemplate [amount=" + amount + ", description=" + description 
				+ ", status_id=" + status_id + ", type_id=" + type_id + ", author_id=" + author_id + "]";
	}
	
	
	
	
}
