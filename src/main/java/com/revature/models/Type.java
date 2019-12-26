package com.revature.models;

public class Type {
	private int id;
	private String reimbursementType;
	
	public Type(int id, String reimbursementType) {
		super();
		this.id = id;
		this.reimbursementType = reimbursementType;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((reimbursementType == null) ? 0 : reimbursementType.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (id != other.id)
			return false;
		if (reimbursementType == null) {
			if (other.reimbursementType != null)
				return false;
		} else if (!reimbursementType.equals(other.reimbursementType))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Type [id=" + id + ", reimbursementType=" + reimbursementType + "]";
	}


}
