package com.lockMgr.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lockMgr.pojo.BasePojo;
import com.lockMgr.pojo.Operator;
import com.lockMgr.pojo.Syslog;



public abstract class BaseDao<T extends BasePojo> extends HibernateDaoSupport
		implements IBaseDao<T>
{
	private Class<T> clazz;
	private String className;

	@SuppressWarnings("unchecked")
	public BaseDao()
	{
		this.clazz = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType)
		{
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.clazz = (Class<T>) p[0];
		}
		className = clazz.getSimpleName();
	}

	public List<T> findByName(String name) throws DataAccessException
	{
		return getHibernateTemplate().find(
				"from " + className + " where name like %" + name + "%");
	}

	public Serializable save(T entity) throws DataAccessException
	{
		if (entity.getId() == null)
			entity.setId(Utils.getNewUUID());
		if (entity.getCreatetime() == null)
			entity.setCreatetime(Utils.getNow());	
		return getHibernateTemplate().save(entity);
	}
    public void save(Collection<T> entities)
    {
    	for(T entity:entities)
    	{
    		save(entity);
    	}
    }
	public void saveOrUpdate(T entity) throws DataAccessException
	{
		if(entity.getId() == null)
			save(entity);
		else {
//			entity.setCreatetime(Utils.getNow());
			getHibernateTemplate().saveOrUpdate(entity);
		}

	}
	
	//更新订单
	public void saveOrder(T entity) {
		if(entity.getId() == null)
			save(entity);
		else
			getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdate(Collection<T> entities) throws DataAccessException
	{
		getHibernateTemplate().saveOrUpdateAll(entities);

	}

	public void delete(T entity) throws DataAccessException
	{
		getHibernateTemplate().delete(entity);

	}

	public void delete(Serializable id) throws DataAccessException
	{
		T entity = this.load(id);
		delete(entity);

	}

	@SuppressWarnings("rawtypes")
	public void delete(Collection entities) throws DataAccessException
	{
		getHibernateTemplate().deleteAll(entities);

	}

	public T load(Serializable id) throws DataAccessException
	{

		return getHibernateTemplate().load(clazz, id);
	}

	public List publicFind(String queryString) throws DataAccessException
	{
		return getHibernateTemplate().find(queryString);
	}

	public List<T> find(String queryString) throws DataAccessException
	{
		return getHibernateTemplate().find(queryString);
	}

	public List<T> find(String queryString, Object[] objs)
			throws DataAccessException
	{
		if (null != objs && objs.length > 0)
			return getHibernateTemplate().find(queryString, objs);
		return getHibernateTemplate().find(queryString);
	}

	public List<T> find(T example) throws DataAccessException
	{
		return getHibernateTemplate().findByExample(example);
	}

	public T findById(Serializable id) throws DataAccessException
	{
		return getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object obj) throws DataAccessException
	{
		return getHibernateTemplate().find(hql, obj);
	}

	public List<T> findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) throws DataAccessException
	{
		if (pageSize == -1)
			return this.find(hql, values);
		List list = getHibernateTemplate().executeFind(new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				if (null != values && values.length > 0)
					for (int i = 0; i < values.length; i++)
					{
						query.setParameter(i, values[i]);
					}
			  String sql=	query.getQueryString();
				List result = query.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	public List<T> findByProperty(String fieldName, Object value)
			throws DataAccessException
	{
		String sql = "from " + className + " where " + fieldName + "=?";
		return getHibernateTemplate().find(sql, value);
	}

	public void executeQuery(final String hql) throws DataAccessException
	{
		getSession().doWork(new Work()
		{
			public void execute(Connection conn) throws SQLException
			{
				conn.createStatement().execute(hql);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object uniqueResult(final String hql) throws DataAccessException
	{
		Object obj = getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				Object result = query.uniqueResult();
				return result;
			}
		});
		return obj;
	}

	public Long getCount() throws DataAccessException
	{
		Object obj=uniqueResult("select count(*) from " + className);
		if(obj==null)
		{
			return 0l;
		}
		else 
		{
			return (Long)obj;
		}
	}

	public Long getCount(String hql) throws DataAccessException
	{
		return (Long) uniqueResult(hql);
	}

	public List<T> findByProperties(String[] fields, Object[] values)
			throws DataAccessException
	{
		String hql = "from " + className + " where ";
		for (int i = 0; i < fields.length; i++)
		{
			hql += fields[i] + "=? and ";
		}
		hql += "1=1";
		return getHibernateTemplate().find(hql, values);
	}

	public List<T> listAll() throws DataAccessException
	{
		return getHibernateTemplate().loadAll(clazz);
	}

	public List findBySQL(final String sql) throws DataAccessException
	{
		List list = getHibernateTemplate().executeFind(new HibernateCallback()
		{
			public List doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				Query query = session.createSQLQuery(sql);
				return query.list();
			}

		});
		return list;
	}

	protected Serializable saveLog(Syslog log) throws DataAccessException
	{
		log.setId(Utils.getNewUUID());
		log.setCreatetime(Utils.getNow());
		return getHibernateTemplate().save(log);
	};

	public void writeLog(Operator operator, String operation,
			String objectType, T example)
	{
		Syslog log = new Syslog();
		log.setUserId(operator.getId());
		if (null == example)
		{
			log.setName(operation);
			log.setComment("【" + operator.getAccount() + "】" + operation
					+ objectType);
			saveLog(log);
			return;
		}
		if (operation.equals("保存"))
		{
			if (null == example.getId())
			{
				operation = "添加";
			}
			else
			{
				operation = "修改";
			}
		}
		log.setName(operation);
		log.setComment("【" + operator.getAccount() + "】" + operation
				+ objectType + "【" + example.getName() + "】");
		saveLog(log);

	}

	public void fillPagetoMap(Map map, final String hql, final Object[] values,
			final int page, final int pageSize)
	{
		Long count = getCount(hql, values);
		List<T> exampleList = findByPage(hql, values, (page - 1) * pageSize,
				pageSize);

		map.put("total", count);
		map.put("rows", exampleList);
		return;

	}

	public Long getCount(String hql, final Object[] values)
			throws DataAccessException
	{
		String countHql = "select count(*) "
				+ hql.substring(hql.indexOf("from"));
		Object obj= uniqueResult(countHql, values);
		if(obj==null)
		{
			return  0l;
		}
		else
		{
			return (Long) obj;
		}
	}

	public Object uniqueResult(final String hql, final Object[] values)
			throws DataAccessException
	{
		Object obj = getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				if (null != values && values.length > 0)
				{
					for (int i = 0; i < values.length; i++)
					{
						query.setParameter(i, values[i]);
					}
				}
				Object result = query.uniqueResult();
				String sql=query.getQueryString();
				return result;
			}
		});
		return obj;
	}
	
	public List publicFind(String queryString,Object[] objs) throws DataAccessException
	{
		if (null != objs && objs.length > 0)
			return getHibernateTemplate().find(queryString, objs);
		return getHibernateTemplate().find(queryString);
	}
	
	/**
	 * 计算总页数
	 * @param listNums 集合总条数(size)
	 * @param pageSize 需要分页的页容量
	 * @return
	 */
	private int calculateTotalPage(int listNums, int pageSize) {
		int totalPage = 0;
		if(listNums > pageSize) {
			if(listNums % pageSize == 0)
				totalPage = listNums / pageSize;
			else 
				totalPage = listNums / pageSize + 1;
		} else {
			totalPage = 1;
		}
		return totalPage;
	}
	
	//得到一条总页数
	public int getTotalPage(String hql, int pageSize) {
		int totalPage = 0;
		List<Long> rowsList = publicFind(hql);
		if(rowsList.get(0) != null) {
			Long rows = rowsList.get(0);
			if(rows % pageSize == 0)
				totalPage = (int) (rows / pageSize);
			else 
				totalPage = (int) (rows / pageSize + 1);
		}
		return totalPage;
	}


}
