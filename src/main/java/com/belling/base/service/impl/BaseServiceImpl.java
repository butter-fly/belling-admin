package com.belling.base.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.belling.base.exception.MapperException;
import com.belling.base.mapper.BaseMapper;
import com.belling.base.model.Pagination;
import com.belling.base.model.PersistentObject;
import com.belling.base.service.BaseService;
import com.belling.base.util.LoggerUtils;

 

 

/**
 * Service基类，实现了数据的CRUD
 * 
 * @param <DAO>
 * @param <T>
 * @param <ID>
 * @author Sunny
 */
public abstract class BaseServiceImpl<Mapper extends BaseMapper<T, ID>, T extends PersistentObject, ID extends Serializable>
		implements BaseService<T, ID> {

	/**
	 * 由子类注入实体DAO
	 */
	protected Mapper mapper;

	public abstract void setMapper(Mapper mapper);

	/**
	 * 查询所有分页
	 * 
	 * @param p
	 * @return
	 */
	public Pagination<T> findByAllPagination(Pagination<T> p) {
		mapper.findByAll(p);
		return p;
	}

	/**
	 * 通过主键查询实体
	 * 
	 * @param PK pk
	 * @return T
	 */
	public T get(ID pk) {
		return mapper.get(pk);
	}

	/**
	 * 通过主键集合查询实体
	 * 
	 * @param List
	 *            <PK> pks
	 * @return List<T>
	 */
	public List<T> get(Collection<ID> pks) {
		List<T> list = new ArrayList<T>(pks.size());
		for (ID pk : pks) {
			list.add(get(pk));
		}
		return list;
	}

	/**
	 * 插入/更新实体
	 * 
	 * @param T t
	 * 
	 */
	public void save(T t) {
		if (t.getId() == null) {
			mapper.insert(t);
		} else {
			mapper.update(t);
		}
	}

	/**
	 * 插入/更新实体集合
	 * 
	 * @param List <T> ts
	 */
	public void save(Collection<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param T t
	 */
	public void update(T t) {
		verifyRows(mapper.update(t), 1, "数据库更新失败");
	}

	/**
	 * 更新实体集合
	 * 
	 * @param List
	 *            <T> ts
	 */
	public void update(Collection<T> ts) {
		for (T t : ts) {
			update(t);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param T
	 *            t
	 */
	@SuppressWarnings("unchecked")
	public void delete(T t) {
		deleteById((ID) t.getId());
	}

	/**
	 * 删除实体集合
	 * 
	 * @param List
	 *            <T> ts
	 */
	public void delete(Collection<T> ts) {
		for (T t : ts) {
			delete(t);
		}
	}

	/**
	 * 通过主键删除实体
	 * 
	 * @param PK pk
	 * @return T
	 */
	public void deleteById(ID id) {
		deleteById(Arrays.asList(id));
	}

	/**
	 * 通过主键集合删除实体 注：这里别把List改为Collection，会导致覆盖方法的List<ID>调用不到
	 * 
	 * @param List <PK> pks
	 * @return List<T>
	 */
	public void deleteById(List<ID> idList) {
		verifyRows(mapper.deleteById(idList), idList.size(), "数据库删除失败");
	}

	/**
	 * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
	 * 
	 * @param updateRows
	 * @param rows
	 * @param message
	 */
	protected void verifyRows(int updateRows, int rows, String message) {
		if (updateRows != rows) {
			MapperException e = new MapperException(message);
			LoggerUtils.fmtError(BaseService.class, e, "need update is {}, but real update rows is {}.", rows, updateRows);
			throw e;
		}
	}
	
	
	/**
	 * 集合排序
	 * 
	 * 对List对象按照某个成员变量进行排序
	 *  
	 * @param list List对象
	 * @param sortField 排序的属性名称
	 * @param sortMode 排序方式：ASC，DESC 任选其一
	 */
	@SuppressWarnings("hiding")
	protected synchronized <T> void sortList(List<T> list, final String sortField, final String sortMode) {
		Collections.sort(list, new Comparator<T>() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@SuppressWarnings("rawtypes")
			@Override
			public int compare(T o1, T o2) {
				try {
					Class clazz = o1.getClass();
					Field field = clazz.getDeclaredField(sortField); // 获取成员变量
					field.setAccessible(true); // 设置成可访问状态
					String typeName = field.getType().getName().toLowerCase(); // 转换成小写

					Object v1 = field.get(o1); // 获取field的值
					Object v2 = field.get(o2); // 获取field的值

					boolean ASC_order = (sortMode == null || "ASC".equalsIgnoreCase(sortMode));

					// 判断字段数据类型，并比较大小
					if (typeName.endsWith("string")) {
						String value1 = v1.toString();
						String value2 = v2.toString();
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("short")) {
						Short value1 = Short.parseShort(v1.toString());
						Short value2 = Short.parseShort(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("byte")) {
						Byte value1 = Byte.parseByte(v1.toString());
						Byte value2 = Byte.parseByte(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("char")) {
						Integer value1 = (int) (v1.toString().charAt(0));
						Integer value2 = (int) (v2.toString().charAt(0));
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("int") || typeName.endsWith("integer")) {
						Integer value1 = Integer.parseInt(v1.toString());
						Integer value2 = Integer.parseInt(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("long")) {
						Long value1 = Long.parseLong(v1.toString());
						Long value2 = Long.parseLong(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("float")) {
						Float value1 = Float.parseFloat(v1.toString());
						Float value2 = Float.parseFloat(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("double")) {
						Double value1 = Double.parseDouble(v1.toString());
						Double value2 = Double.parseDouble(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("boolean")) {
						Boolean value1 = Boolean.parseBoolean(v1.toString());
						Boolean value2 = Boolean.parseBoolean(v2.toString());
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("date")) {
						Date value1 = (Date) (v1);
						Date value2 = (Date) (v2);
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else if (typeName.endsWith("timestamp")) {
						Timestamp value1 = (Timestamp) (v1);
						Timestamp value2 = (Timestamp) (v2);
						return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
					} else {
						// 调用对象的compareTo()方法比较大小
						Method method = field.getType().getDeclaredMethod("compareTo", new Class[] { field.getType() });
						method.setAccessible(true); // 设置可访问权限
						int result = (Integer) method.invoke(v1, new Object[] { v2 });
						return ASC_order ? result : result * (-1);
					}
				} catch (Exception e) {
					String err = e.getLocalizedMessage();
					System.out.println(err);
					e.printStackTrace();
				}
				return 0; // 未知类型，无法比较大小
			}
		});
	}
}
