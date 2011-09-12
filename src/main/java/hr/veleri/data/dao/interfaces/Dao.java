package hr.veleri.data.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import hr.veleri.data.dataobjects.DomainObject;


public interface Dao<T extends DomainObject>
{
	public void delete(T o);

	public T load(Serializable id);

	public T save(T o);

	public List<T> findAll();

	public int countAll();
}

