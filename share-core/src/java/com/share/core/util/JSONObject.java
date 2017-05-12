package com.share.core.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public final class JSONObject {
	private static final Gson gson = new GsonBuilder().create();
	private static final Logger logger = LoggerFactory.getLogger(JSONObject.class);
	private HashMap<String, Object> json = new HashMap<String, Object>();

	/**
	 * 构造函数
	 */
	public JSONObject() {
	}

	/**
	 * json解码
	 * @param json
	 * @return
	 * @throws JsonSyntaxException
	 */
	public JSONObject(String json) {
		HashMap<String, Object> jsonMap = decode(json, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		if (jsonMap != null) {
			this.json.putAll(jsonMap);
		}
	}

	/**
	 * json解码
	 * @param json
	 * @return
	 */
	public static JSONObject decode(String json) {
		return new JSONObject(json);
	}

	/**
	 * json转任意类型
	 * @param json
	 * @param typeOfT 类型
	 * @return T
	 */
	public static <T> T decode(String json, Type typeOfT) {
		try {
			return gson.fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * json编码
	 * @param src
	 * @return String
	 */
	public static String encode(Object src) {
		return gson.toJson(src).trim();
	}

	/**
	 * put
	 * @param key
	 * @param value
	 */
	public JSONObject put(String key, Object value) {
		json.put(key, value);
		return this;
	}

	/**
	 * remove
	 * @author ruan
	 * @param key
	 */
	public Object remove(String key) {
		return json.remove(key);
	}

	/**
	 * 清空整个json
	 * @author ruan
	 */
	public void clear() {
		json.clear();
	}

	/**
	 * 获取json的key的数量
	 * @author ruan 
	 */
	public int size() {
		return json.size();
	}

	/**
	 * 判断某个key是否存在
	 * @param key 键
	 */
	public boolean containsKey(String key) {
		return json.containsKey(key);
	}

	/**
	 * getObject
	 * @param key
	 */
	public Object getObject(String key) {
		return json.get(key);
	}

	/**
	 * getString
	 * @param key
	 */
	public String getString(String key) {
		return StringUtil.getString(getObject(key));
	}

	/**
	 * getInt
	 * @param key
	 */
	public int getInt(String key) {
		return StringUtil.getInt(getObject(key));
	}

	/**
	 * getByte
	 * @author ruan
	 * @param key
	 */
	public byte getByte(String key) {
		return StringUtil.getByte(getObject(key));
	}

	/**
	 * getShort
	 * @author ruan
	 * @param key
	 */
	public short getShort(String key) {
		return StringUtil.getShort(getObject(key));
	}

	/**
	 * getLong
	 * @param key
	 */
	public long getLong(String key) {
		return StringUtil.getLong(getObject(key));
	}

	/**
	 * getDouble
	 * @param key
	 */
	public double getDouble(String key) {
		return StringUtil.getDouble(getObject(key));
	}

	/**
	 * getFloat
	 * @param key
	 */
	public float getFloat(String key) {
		return StringUtil.getFloat(getObject(key));
	}

	/**
	 * getBoolean
	 * @param key
	 */
	public boolean getBoolean(String key) {
		return StringUtil.getBoolean(getObject(key));
	}

	/**
	 * getJSON
	 * @param key
	 */
	public JSONObject getJSON(String key) {
		return new JSONObject(JSONObject.encode(getObject(key)));
	}

	/**
	 * getList
	 * @param key
	 */
	public ArrayList<Object> getList(String key) {
		return decode(getString(key), new TypeToken<ArrayList<Object>>() {
		}.getType());
	}

	/**
	 * getSet
	 * @param key
	 */
	public HashSet<Object> getSet(String key) {
		return decode(getString(key), new TypeToken<HashSet<Object>>() {
		}.getType());
	}

	/**
	 * 转成
	 * @author ruan 
	 * @return
	 */
	public Map<String, Object> toMap() {
		return json;
	}

	/**
	 * toString
	 */
	public String toString() {
		return encode(json);
	}

	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return json.isEmpty();
	}

	/**
	 * 哈希码
	 * @author ruan
	 * @return
	 */
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * 是否相等
	 * @author ruan
	 * @param o
	 * @return
	 */
	public boolean equals(Object o) {
		if (o instanceof JSONObject) {
			return o.hashCode() == hashCode();
		}
		return false;
	}
}