package com.revature.dao;

import java.io.Serializable;
import java.util.List;

public interface DaoForAll<T, I extends Serializable> {
	List<T> getAll();
	T getById(I id);
	T add(T obj);
	boolean update(T obj);
	boolean delete(T obj);
}
