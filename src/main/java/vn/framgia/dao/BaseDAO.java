package vn.framgia.dao;

import java.io.Serializable;

import org.hibernate.LockMode;

public interface BaseDAO<PK, T> {
	void delete(T entity);

	T saveOrUpdate(T entity);

	T findById(Serializable key);
	
	T findByIdUsingLock(Serializable key, LockMode lockMode);
}
