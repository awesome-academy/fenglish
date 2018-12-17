package vn.framgia.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public interface BaseService<PK, T> {
	public T findById(Serializable key);

	public T saveOrUpdate(T entity) throws IllegalAccessException, InvocationTargetException;

	public boolean delete(T entity) throws IllegalAccessException, InvocationTargetException;
}
