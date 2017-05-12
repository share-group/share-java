package com.share.core.mongo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.share.core.annotation.AsMongoId;

/**
 * mongodb操作类
 * 
 * @author ruan 2013-7-22
 */
public final class Mongodb {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Mongodb.class);
	/**
	 * mongo对象
	 */
	private MongoClient mongo;
	/**
	 * mongo db对象
	 */
	private MongoDatabase mongoDatabase;

	/**
	 * mongodb地址
	 */
	@Value("${mongodb.host}")
	private String host;
	/**
	 * mongodb端口
	 */
	@Value("${mongodb.port}")
	private int port;
	/**
	 * mongodb用户名
	 */
	@Value("${mongodb.user}")
	private String user;
	/**
	 * mongodb密码
	 */
	@Value("${mongodb.password}")
	private String password;
	/**
	 * mongodb数据库名
	 */
	@Value("${mongodb.dbName}")
	private String dbName;

	/**
	 * 空的DBObject
	 */
	private final static BasicDBObject emptyBasicDBObject = new BasicDBObject();

	/**
	 * 构造函数
	 */
	private Mongodb() {
	}

	/**
	 * 初始化	
	 */
	public void init() {
		// mongodb连接选项
		MongoClientOptions options = new MongoClientOptions.Builder().build();

		// mongodb地址列表
		List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>(1);
		serverAddressList.add(new ServerAddress(host, port));

		if (password.isEmpty()) {
			// 无密码连接
			mongo = new MongoClient(serverAddressList, options);
		} else {
			// 有密码连接
			List<MongoCredential> credentialsList = new ArrayList<MongoCredential>(1);
			credentialsList.add(MongoCredential.createCredential(user, dbName, password.toCharArray()));
			mongo = new MongoClient(serverAddressList, credentialsList, options);
		}
		mongoDatabase = mongo.getDatabase(dbName);
		logger.info("mongodb init " + host + ":" + port + ", database: " + dbName);
	}

	/**
	 * 保存单条数据
	 * 
	 * @author ruan 2013-7-25
	 * @param clazz 表对应的实体类名
	 */
	public <T> void save(T clazz) {
		Class<?> cla = clazz.getClass();
		mongoDatabase.getCollection(getClazzName(cla)).insertOne(data2Document(cla, clazz));
	}

	/**
	 * 保存列表数据
	 * @author ruan 2013-7-25
	 * @param clazzList 实体类列表
	 */
	public <T> void save(ArrayList<T> clazzList) {
		List<Document> list = new ArrayList<Document>();
		for (T t : clazzList) {
			list.add(data2Document(t.getClass(), t));
		}
		mongoDatabase.getCollection(getClazzName(clazzList.get(0).getClass())).insertMany(list);
	}

	/**
	 * 查询某个表的所有数据
	 * 
	 * @author ruan 2013-7-26
	 * @param clazz 表对应的实体类名
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> List<T> find(Class<T> clazz) {
		return find(clazz, null);
	}

	/**
	 * 根据条件查询某个表的数据
	 * 
	 * @author ruan 2013-7-27
	 * @param clazz 表对应的实体类名
	 * @param query 查询条件
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public <T> List<T> find(Class<T> clazz, BasicDBObject query) {
		return find(clazz, query, null);
	}

	/**
	 * 根据条件查询某个表的数据并排序
	 * @author ruan
	 * @param clazz 表对应的实体类名
	 * @param query 查询条件
	 * @param orderBy 排序条件(k=>1 or -1)
	 * @return
	 */
	public <T> List<T> find(Class<T> clazz, BasicDBObject query, BasicDBObject orderBy) {
		return find(clazz, query, orderBy, 0);
	}

	/**
	 * 根据条件查询某个表的数据
	 * @author ruan
	 * @param clazz 表对应的实体类名
	 * @param query 查询条件
	 * @param limit 数据限制条数
	 * @return
	 */
	public <T> List<T> find(Class<T> clazz, BasicDBObject query, int limit) {
		return find(clazz, query, null, limit);
	}

	/**
	 * 根据条件查询某个表的数据并排序
	 * @author ruan
	 * @param clazz 表对应的实体类名
	 * @param query 查询条件
	 * @param orderBy 排序条件(k=>1 or -1)
	 * @param limit 数据限制条数
	 * @return
	 */
	public <T> List<T> find(Class<T> clazz, BasicDBObject query, BasicDBObject orderBy, int limit) {
		FindIterable<Document> iterable = mongoDatabase.getCollection(getClazzName(clazz)).find(query == null ? emptyBasicDBObject : query).sort(orderBy == null ? emptyBasicDBObject : orderBy);
		if (limit > 0) {
			iterable.limit(limit);
		}
		List<T> result = new ArrayList<T>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			result.add(document2Data(cursor.next(), clazz));
		}
		cursor.close();
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @author ruan 2013-7-28
	 * @param clazz
	 * @param query
	 */
	public <T> void delete(Class<T> clazz) {
		mongoDatabase.getCollection(getClazzName(clazz)).drop();
	}

	/**
	 * 获取所有表的表名
	 * @author ruan
	 * @return
	 */
	public Set<String> getCollectionNames() {
		Set<String> set = new LinkedHashSet<>();
		MongoCursor<String> it = mongoDatabase.listCollectionNames().iterator();
		while (it.hasNext()) {
			set.add(it.next());
		}
		return set;
	}

	/**
	 * 删除所有表
	 * @author ruan
	 */
	public void dropAll() {
		for (String collection : getCollectionNames()) {
			mongoDatabase.getCollection(collection).drop();
		}
	}

	/**
	 * toString
	 * @author ruan
	 * @return
	 */
	public String toString() {
		return mongoDatabase.getName();
	}

	/**
	 * hashCode
	 * @author ruan
	 * @return
	 */
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * equals
	 * @author ruan
	 * @param o
	 * @return
	 */
	public boolean equals(Object o) {
		if (o instanceof Mongodb) {
			return hashCode() == ((Mongodb) o).hashCode();
		}
		return false;
	}

	/**
	 * 把一个实体类转化为一个DBObject
	 * 
	 * @author ruan 2013-7-25
	 * @param cla
	 * @param clazz
	 */
	private <T> Document data2Document(Class<?> cla, T clazz) {
		Document document = new Document();
		try {
			// 获取所有属性
			for (Field field : cla.getDeclaredFields()) {
				String filedName = field.getName();
				if (field.getAnnotation(AsMongoId.class) == null) {
					document.put(filedName, getGetMethod(cla, filedName).invoke(clazz));
				} else {
					document.put("_id", getGetMethod(cla, filedName).invoke(clazz));
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return document;
	}

	/**
	 * 把一个DBObject转化为一个实体类
	 * 
	 * @author ruan 2013-7-26
	 * @param dbObject
	 * @param cla
	 */
	private <T> T document2Data(Document document, Class<T> cla) {
		try {
			T obj = cla.newInstance();
			// 获取所有属性
			for (Field field : cla.getDeclaredFields()) {
				Method method = getSetMethod(cla, field.getName(), field.getType());
				if (field.getAnnotation(AsMongoId.class) == null) {
					method.invoke(obj, document.get(field.getName()));
				} else {
					method.invoke(obj, document.get("_id"));
				}
			}
			return obj;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 获取类名
	 * 
	 * @author ruan 2013-7-25
	 * @param cla
	 */
	private String getClazzName(Class<?> cla) {
		return cla.getSimpleName().substring(1).toLowerCase();
	}

	/**
	 * 获取get方法名
	 * 
	 * @author ruan 2013-7-25
	 * @param cla
	 * @param fieldName
	 */
	private <T> Method getGetMethod(Class<T> cla, String fieldName) {
		try {
			return cla.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 获取set方法名
	 * 
	 * @author ruan 2013-7-26
	 * @param cla
	 * @param fieldName
	 */
	private <T> Method getSetMethod(Class<T> cla, String fieldName, Class<?>... args) {
		try {
			return cla.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), args);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		mongo.close();
		logger.info("mongodb closed");
	}
}