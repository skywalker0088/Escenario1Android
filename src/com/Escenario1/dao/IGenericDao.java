package com.Escenario1.dao;

import java.io.Serializable;
import java.util.List;

public interface  IGenericDao  <T, ID extends Serializable>{
	void create(T entity) throws Exception;

	T retriveById(ID id ) throws Exception;

	List<T> retrieveAll()  throws Exception;

	void update(T entity) throws Exception;

	void delete(T entity) throws Exception;
}
