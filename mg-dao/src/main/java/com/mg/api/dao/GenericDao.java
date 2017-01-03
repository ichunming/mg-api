package com.mg.api.dao;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {

	/**
	 * insert entity
	 * 
	 * @param entity
	 */
	public void insert(T entity);

	/**
	 * update entity
	 * 
	 * @param entity
	 * @return the number of update, normally return 1
	 */
	public void update(T entity);

	/**
	 * get record by primary key
	 * 
	 * @param primaryKey
	 * @return if no record, return null
	 */
	public T select(PK primaryKey);

	/**
	 * delete by primary key
	 * 
	 * @param primaryKey
	 * @return the number of delete, normally return 1
	 */
	public void delete(PK primaryKey);

}
