package com.sc.tradmaster.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.sc.tradmaster.bean.User;
/**
 * 
 * @author grl 2017-01-01
 *
 */
public interface BaseDao {
	/** 保存指定的持久化对象 */
	public void save(Object obj);
	
	/** 保存或更新指定的持久化对象 */
	public void saveOrUpdate(Object obj);
	
	/** 删除指定ID的持久化对象 */
	public void deleteById(Class clazz, Serializable id);
	
	/** 删除指定ID的持久化对象 */
	public void delete(Object obj);
	
	/** 加载指定ID的持久化对象 */
	public Object loadById(Class clazz, Serializable id);
	
	/**加载满足条件的持久化对象*/
	public Object loadObject(String hql);
	
	/** 查询指定类的满足条件的持久化对象 */
	public List findByHql(String hql);
	
	/** 装载指定类的查询结果 */
	public List findInProperty(String clazz, String propertyName, String value);
	
	/** 装载指定类的查询结果 */
	public List findLikeProperty(String clazz, String propertyName, String value);
	
	/** 装载指定类的查询结果 */
	public List findByProperty(String clazz, String propertyName, Object value);
	
	/** 装载指定类的查询结果 */
	public List findByProperty(String clazz, String[] propertyName, Object[] value);
	
	/** 装载指定类的所有持久化对象 */
	public List listAll(String clazz);
	
	/** 分页装载指定类的所有持久化对象 */
	public List listAll(String clazz, int start, int limit);
	
	/** 条件更新数据 */
	public int update(String hql);
	
	/** 统计指定类的所有持久化对象 */
	public int countAll(String clazz);

	/** 统计指定类的查询结果 */
	public int countQuery(String hql);
	
	/** 统计指定类的指定属性查询结果 */
	public int countQueryByclazzAndProperty(String clazz,String propertyname,String valuename, String property,String value);
	
	/** 统计指定类的查询结果 */
	public int count(String clazz,String[] property,Object[] value) ;
	
	/** 分页装载指定类的查询结果 */
	public List findInProperty(String clazz, String propertyName, String value, int start, int limit);
	
	/** 分页装载指定类的查询结果 */
	public List findLikeProperty(String clazz, String propertyName, String value, int start, int limit);
	
	/** 分页装载指定类指定属性的查询结果 */
	public List findClazzByPropertyAndValue(String clazz, String[] property,
			String[] value, String propertyname, String usernum, int start, int limit);
	
	/** 分页装载指定类的查询结果 */
	public List findByProperty(String clazz,String property,String valuename, String propertyName, String value, int start, int limit);
	
	/** 分页装载指定类的查询结果 */
	public List findByProperty(String clazz, String[] propertyName, Object[] value, int start, int limit);

	/** 分页装载指定类的查询结果 like*/
	public List findByPropertyLike(String clazz, String[] propertyName, Object[] value, int start, int limit);
	
	/** 分页查询指定类的满足条件的持久化对象 */
	public List findByHql(String hql, int start, int limit);
	
	/**
	 * 分页，搜索，排序（三个条件排序）
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @param orderName1
	 * @param orderName2
	 * @param orderName3
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findByProperty3(String clazz, String[] propertyName, Object[] value, String orderName1,String orderName2,String orderName3, int start, int limit);
	
	
	/**
	 * 分页，搜索，排序（两个）
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @param orderName
	 * @param orderValue
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findByProperty(String clazz, String[] propertyName, Object[] value, String orderName1,String orderName2, int start, int limit);
	
	
	/**
	 * 分页，搜索，排序(一个)
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @param orderName
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findByProperty1(String clazz, String[] propertyName, Object[] value, String orderName, int start, int limit);


	/** sum值统计 */
	public int findSum(String hql);
		
	/**
	 * 分页，搜索，排序
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @param orderName
	 * @param orderType
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findByProperty(String clazz, String[] propertyName,
			Object[] value, String[] orderName, String[] orderType, int start,
			int limit);
	
	public List findPageAndOrder(String hql, String[] orderName, String[] orderType, int start,
			int limit);

	
	
	/**
	 * 分页查询指定类的时间的持久化对象 
	 * @param hql
	 * @param startTime
	 * @param endTime
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findByHqlAndDate(String hql,final Date startTime,final Date endTime, int start, int limit);

	/** 
	 * 分页装载指定类的查询结果
	 * 排序 时间段的查询22
	 */
    public List findByProperty5(String clazz, String[] propertyName, Object[] value, 
				String timeName,String orderName1,final String startTime,final String endTime,int start, int limit);
	
    /**
     * 装载指定类的查询结果
     * @param clazz
     * @param propertyName
     * @param value
     * @param timeName
     * @param orderName1
     * @param startTime
     * @param endTime
     * @return
     */
    public List findByProperty6(String clazz, String[] propertyName, Object[] value, 
			String timeName,String orderName1,final String startTime,final String endTime);
    
    public int excuteBySql(String sql);
    
    
}
